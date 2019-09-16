package com.xuyao.test.http.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.nio.charset.Charset;

public class Server {

    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) {
            ch.pipeline()
                    //指定分隔符
//                    .addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("##".getBytes())))
                    //换行符
//                    .addLast(new LineBasedFrameDecoder(50))
//                    .addLast(new DelimiterBasedFrameDecoder(50, Delimiters.lineDelimiter()))
                    //指定消息长度
                    .addLast(new FixedLengthFrameDecoder(15))
                    .addLast(new ServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8888;
        new Server().bind(port);
    }

    class ServerHandler extends SimpleChannelInboundHandler<Object> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
            ByteBuf buf = (ByteBuf) msg;
            String body = buf.toString(CHARSET_UTF8);

            System.out.println("read from client : " + body);
            //换行符
//            body = body + System.getProperty("line.separator");
            //指定分隔符
//            body = body + "##";
            ByteBuf resp = Unpooled.copiedBuffer(body.getBytes(CHARSET_UTF8));
            ctx.write(resp);
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
