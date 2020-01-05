package com.xuyao.test.http.rpc;

import com.xuyao.test.http.rpc.service.IGreetService;
import com.xuyao.test.http.rpc.service.impl.GreatServiceImpl;
import com.xuyao.test.http.rpc.transmission.RequestData;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static IGreetService greetService = new GreatServiceImpl();

    private static Map<String, Object> nameInstanceMap = new HashMap<>();

    public void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            bootstrap.bind(port).sync();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) {
            ch.pipeline()
            .addLast(new ObjectEncoder())
            .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
            .addLast(new ServerHandler());
        }
    }

    public static void main(String[] args) {
        nameInstanceMap.put(IGreetService.class.getName(), greetService);
        new Server().bind(8080);
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
