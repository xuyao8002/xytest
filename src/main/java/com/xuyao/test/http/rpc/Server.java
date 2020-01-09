package com.xuyao.test.http.rpc;

import com.xuyao.test.http.rpc.annotation.Producer;
import com.xuyao.test.http.rpc.transmission.RequestData;
import com.xuyao.test.http.rpc.transmission.SpecialValue;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Server {

    private static int port = 8888;

    private static Map<String, Object> nameInstanceMap = new HashMap<>();


    public void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new serverChannelHandler());
            bootstrap.bind(port).sync();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private class serverChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) {
            ch.pipeline()
            .addLast(new ObjectEncoder())
            .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
            .addLast(new ServerHandler());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        initNameInstanceMap();
        new Server().bind(port);
    }

    private static void initNameInstanceMap() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String packageName = "com.xuyao.test.http.rpc.service.impl";
        URL resource = Server.class.getResource("/"+packageName.replace(".", "/"));
        if(resource != null){
            String filePath = resource.getFile();
            File files = new File(filePath);
            if(files != null){
                String[] fileNames = files.list();
                ClassLoader classLoader = new ClassLoader() {
                    @Override
                    public Class<?> loadClass(String name) throws ClassNotFoundException {
                        return super.loadClass(name);
                    }
                };
                String className;
                for (String fileName : fileNames) {
                    className = packageName + "." + fileName.substring(0, fileName.lastIndexOf("."));
                    Class<?> serviceClass = classLoader.loadClass(className);
                    if(serviceClass != null){
                        Producer producer = serviceClass.getAnnotation(Producer.class);
                        String name;
                        if(producer != null && StringUtils.isNotBlank(name = producer.name())){
                            nameInstanceMap.put(name, serviceClass.newInstance());
                        }
                    }
                }
            }
        }
    }

    class ServerHandler extends SimpleChannelInboundHandler<Object> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
            try{
                RequestData requestData = (RequestData) msg;
                System.out.println("接收：" + requestData);
                String methodName = requestData.getMethodName();
                String serviceName = requestData.getServiceName();
                Class[] argsTypes = requestData.getArgsTypes();
                Object[] args = requestData.getArgs();
                Object service = nameInstanceMap.get(serviceName);
                Method method = service.getClass().getMethod(methodName, argsTypes);
                if(method.getReturnType() != void.class){
                    Object invoke = method.invoke(service, args);
                    if(Objects.equals(invoke, null)){
                        invoke = SpecialValue.NULL;
                    }
                    ctx.channel().writeAndFlush(invoke);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }
    }

}
