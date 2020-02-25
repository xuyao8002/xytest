package com.xuyao.test.http.rpc;

import com.xuyao.test.http.rpc.annotation.Consumer;
import com.xuyao.test.http.rpc.service.IGreetService;
import com.xuyao.test.http.rpc.service.IPersonService;
import com.xuyao.test.http.rpc.transmission.Person;
import com.xuyao.test.http.rpc.transmission.RequestData;
import com.xuyao.test.http.rpc.transmission.SpecialValue;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.*;

public class Client {

    private static String serverHost = "localhost";
    private static int serverPort = 8888;


    private static <T> T getService(final Class<T> clazz) {
        T jdkProxy = (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[] {clazz}, new RequestHandler(clazz));
        return jdkProxy;
    }

    private static class RequestHandler<T> implements InvocationHandler {

        final Class<T> clazz;
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        RejectedExecutionHandler policy = new ThreadPoolExecutor.AbortPolicy();
        ExecutorService executorService = new ThreadPoolExecutor(poolSize, poolSize,
                0, TimeUnit.SECONDS, queue, policy);
        RequestHandler(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object object, Method method, Object[] objects)
                throws Throwable {
            RequestData requestData = new RequestData();
            requestData.setMethodName(method.getName());
            Consumer consumer = clazz.getAnnotation(Consumer.class);
            requestData.setServiceName(consumer.ref());
            requestData.setArgsTypes(method.getParameterTypes());
            requestData.setArgs(objects);
            requestData.setReturnType(method.getReturnType());
            Client client = new Client();
            ClientHandler clientHandler = null;
            try {
                clientHandler = client.connect(serverPort, serverHost);
                clientHandler.setRequestData(requestData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return executorService.submit(clientHandler).get();
        }
    }

    public ClientHandler connect(int port, String host) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();
        ClientHandler clientHandler = new ClientHandler();
        try {
            client.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                .addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
                                .addLast(clientHandler);
                        }
                    });
            client.connect(host, port).sync();
        }catch (Exception e){
            e.printStackTrace();
        }
        return clientHandler;
    }

    class ClientHandler extends ChannelInboundHandlerAdapter implements Callable {
        private Object result;
        private RequestData requestData;
        private ChannelHandlerContext context;
        private CountDownLatch serverResponseLatch;

        public void setRequestData(RequestData requestData){
            this.requestData = requestData;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            serverResponseLatch = new CountDownLatch(1);
            context = ctx;

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            //读取服务端返回值
            if(hasReturnType()){
                if(Objects.equals(msg, SpecialValue.NULL)){
                    msg = null;
                }
                result =  msg;
                serverResponseLatch.countDown();
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }

        @Override
        public Object call() throws Exception {
            //发送请求数据
            context.channel().writeAndFlush(requestData);
            //若请求方法有返回值，等待服务端返回数据
            if(hasReturnType()){
                serverResponseLatch.await();
            }
            return result;
        }

        private boolean hasReturnType(){
            return !Objects.equals(requestData.getReturnType(), void.class);
        }
    }

    public static void main(String[] args) {
        IGreetService greetService = getService(IGreetService.class);
        Person tmp = new Person();
        tmp.setName("morning");
        String greeting = greetService.greeting(tmp);
        System.out.println("result: " + greeting);

        IPersonService personService = getService(IPersonService.class);
        Integer id = 11;
        Person person = personService.get(id);
        System.out.println("person: " + person);

        id = 10;
        person = personService.get(id);
        System.out.println("person: " + person);
    }

}
