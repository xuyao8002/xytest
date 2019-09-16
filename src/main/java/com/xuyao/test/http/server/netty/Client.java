package com.xuyao.test.http.server.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.nio.charset.Charset;

public class Client {

    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    public void connect(int port, String host) throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();
        try {
            client.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
//                                    .addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("##".getBytes())))
//                                    .addLast(new LineBasedFrameDecoder(50))
                                    .addLast(new FixedLengthFrameDecoder(15))
                                    .addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = client.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
        int port = 8888;
        Client client = new Client();
        try {
            client.connect(port, "localhost");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClientHandler extends ChannelInboundHandlerAdapter {
        private String sendMsg = "中秋快乐！";
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            for (int i = 0; i < 100; i++) {
                //指定长度
                byte[] req = sendMsg.getBytes(CHARSET_UTF8);
                //换行符
                //byte[] req = (sendMsg + System.getProperty("line.separator")).getBytes(CHARSET_UTF8);
                //指定分隔符
                //byte[] req = (sendMsg + "##").getBytes(CHARSET_UTF8);
                ByteBuf msg = ctx.alloc().buffer();//Unpooled.buffer(req.length);
                msg.writeBytes(req);
                ctx.channel().writeAndFlush(msg);
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf buf = (ByteBuf)msg;
            String body = buf.toString(CHARSET_UTF8);
            System.out.println("read from server: " + body);

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }
    }
}