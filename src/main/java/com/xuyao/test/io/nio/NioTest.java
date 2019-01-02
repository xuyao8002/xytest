package com.xuyao.test.io.nio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest {

    private static String readFilePath = "E:\\file\\lfs.conf";
    private static String writeFilePath = "E:\\file\\writeFile.txt";
    private static String writeFilePath1 = "E:\\file\\writeFile1.txt";

    public static void main(String[] args) throws IOException {
        transferTo();
    }

    public static void channelRead() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(readFilePath, "r");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }


    /**
     * 将数据读到多个buffer中
     *
     * @throws IOException
     */
    private static void scatterRead() throws IOException {
        RandomAccessFile file = new RandomAccessFile(readFilePath, "r");
        FileChannel channel = file.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = {header, body};

        channel.read(bufferArray);
        System.out.println(new String(header.array()));
        System.out.println("---------------------------------------------");
        System.out.println(new String(body.array()));
    }

    /**
     * 将多个buffer中数据写入channel
     *
     * @throws IOException
     */
    private static void gatherWrite() throws IOException {
        RandomAccessFile file = new RandomAccessFile(writeFilePath, "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        String str = "this is header\r\n";
        header.put(str.getBytes("UTF-8"));
        header.flip();
        str = "this is body";
        body.put(str.getBytes());
        body.flip();
        ByteBuffer[] bufferArray = {header, body};

        channel.write(bufferArray);
    }

    private static void transferFrom() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile(writeFilePath, "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile(writeFilePath1, "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    private static void transferTo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile(writeFilePath, "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile(writeFilePath1, "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }

    private void selector() throws IOException {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        channel.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey key;

        while (true) {
            int select = selector.select();
            if (select == 0) continue;
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                key = keyIterator.next();
                if (key.isAcceptable()) {
                    System.out.println("isAcceptable");
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    System.out.println("isConnectable");
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    System.out.println("isReadable");
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    System.out.println("isWritable");
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }

}

