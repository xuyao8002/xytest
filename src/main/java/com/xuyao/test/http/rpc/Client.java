package com.xuyao.test.http.rpc;

import com.xuyao.test.http.rpc.service.IGreetService;
import com.xuyao.test.http.rpc.transmission.Person;
import com.xuyao.test.http.rpc.transmission.RequestData;
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
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private static <T> T createJdkProxy(final Class<T> clazz) {
        IGreetService jdkProxy = (IGreetService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[] { IGreetService.class }, new Client.JdkHandler(clazz));
        return (T)jdkProxy;
    }

    private static class JdkHandler<T> implements InvocationHandler {

        final Class<T> clazz;
        private ExecutorService executorService = Executors.newFixedThreadPool(1);
        JdkHandler(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object object, Method method, Object[] objects)
                throws Throwable {
            RequestData requestData = new RequestData();
            requestData.setMethodName(method.getName());
            requestData.setServiceName(clazz.getName());
            requestData.setArgsTypes(method.getParameterTypes());
            requestData.setArgs(objects);
            int port = 8080;
            Client client = new Client();
            ClientHandler clientHandler = null;
            try {
                if(clientHandler == null){
                    clientHandler = client.connect(port, "localhost");
                }
                clientHandler.setRequestData(requestData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return executorService.submit(clientHandler).get();
        }
    }

    public static void main(String[] args) {
        IGreetService greetService = createJdkProxy(IGreetService.class);
        Person tmp = new Person();
        tmp.setName("morning");
        String greeting = greetService.greeting(tmp);
        System.out.println("result: " + greeting);
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
        private CountDownLatch latch;

        public void setRequestData(RequestData requestData){
            this.requestData = requestData;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            latch = new CountDownLatch(1);
            context = ctx;

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            Class<?> returnType = requestData.getReturnType();
            if(returnType != void.class){
                result =  msg;
                latch.countDown();
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }

        @Override
        public Object call() throws Exception {
            context.channel().writeAndFlush(requestData);
            latch.await();
            return result;
        }
    }

}
