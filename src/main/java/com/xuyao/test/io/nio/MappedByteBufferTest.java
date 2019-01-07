package com.xuyao.test.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedByteBufferTest {
    private static int length = 0x2FFFFFFF;
    private static String file = "E:\\file.txt";
    private static String file1 = "E:\\file1.txt";

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
//        createFile();
//        test();
//        MappedTest();
//        System.out.println(0x8FFFFFF);
//        readWriteTest();
        privateTest();
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start));

    }

    public static void readWriteTest() {
        test(FileChannel.MapMode.READ_WRITE, file);
    }

    public static void privateTest() {
        test(FileChannel.MapMode.PRIVATE, file1);
    }

    public static void test(FileChannel.MapMode mapMode, String file) {
        try {
            FileChannel channel = FileChannel.open(Paths.get(file), StandardOpenOption.READ, StandardOpenOption.WRITE);
            MappedByteBuffer mapBuffer = channel.map(mapMode, 0, length);
            for (int i = 0; i < length; i++) {
                mapBuffer.put((byte) 0);
            }
            mapBuffer.flip();
            while (mapBuffer.hasRemaining()) {
                mapBuffer.get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MappedTest() throws Exception {
        int capacity = 20 * 1024 * 1024;

        RandomAccessFile readFile = new RandomAccessFile(file, "r");
        FileChannel readChannel = readFile.getChannel();
        long fileSize = readChannel.size();
        MappedByteBuffer map = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);

        File file = new File(file1);
        if (!file.exists()) file.createNewFile();
        RandomAccessFile writeFile = new RandomAccessFile(file, "rw");
        FileChannel writeChannel = writeFile.getChannel();
        MappedByteBuffer map1 = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
        map1.put(map);
//        readChannel.close();
//        readFile.close();
//        writeChannel.close();
//        writeFile.close();
    }

    public static void test() throws Exception {
        ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
        byte[] array = new byte[14 * 1024 * 1024];
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file1);
        FileChannel fc = fis.getChannel();
        long timeStar = System.currentTimeMillis();// 得到当前的时间
//       fc.read(byteBuf);// 1 读取
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        System.out.println(fc.size() / 1024);
        long timeEnd = System.currentTimeMillis();// 得到当前的时间
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");
        timeStar = System.currentTimeMillis();
//        fos.write(array);//2.写入
        mbb.flip();
        timeEnd = System.currentTimeMillis();
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");
        fos.flush();
        fc.close();
        fis.close();
    }

    public static void createFile() throws IOException {
        FileWriter fileWriter = new FileWriter(new File(file));
        for (int i = 0; i < 800000; i++) {
            fileWriter.write("1832170449" + i);
            fileWriter.write("\r\n");

        }
        System.out.println("end");
    }
}
