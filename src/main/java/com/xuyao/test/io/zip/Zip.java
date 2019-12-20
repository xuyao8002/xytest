package com.xuyao.test.io.zip;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static String zipName = "";

    private static String sourcePath = "";
    private static String sourceName = "";

    private static int count = 3;
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
//        zipFileNoBuffer();
//        zipFileBuffer();
//        zipFileChannel();
//        zipFileMap();
        System.out.println("花费时间：" + (System.currentTimeMillis() - start));
    }

    public static void zipFileMap() throws Exception{
        File zipFile = new File(zipName);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < count; i++) {
            FileChannel fileChannel = new RandomAccessFile(sourcePath + sourceName, "r").getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            String[] split = sourceName.split("\\.");
            zipOut.putNextEntry(new ZipEntry(split[0] + i + "." + split[1]));
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOut);
            writableByteChannel.write(mappedByteBuffer);
        }
        zipOut.close();
    }

    public static void zipFileChannel() throws Exception{
        File zipFile = new File(zipName);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < count; i++) {
            FileChannel fileChannel = new FileInputStream(sourcePath + sourceName).getChannel();
            String[] split = sourceName.split("\\.");
            zipOut.putNextEntry(new ZipEntry(split[0] + i + "." + split[1]));
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOut);
            fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
        }
        zipOut.close();
    }

    public static void zipFileBuffer() throws Exception{
        File zipFile = new File(zipName);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < count; i++) {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourcePath + sourceName));
            String[] split = sourceName.split("\\.");
            zipOut.putNextEntry(new ZipEntry(split[0] + i + "." + split[1]));
            BufferedOutputStream bufOut = new BufferedOutputStream(zipOut);
            int temp = 0;
            while ((temp = input.read()) != -1) {
                bufOut.write(temp);
            }
            input.close();
        }
        zipOut.close();
    }

    public static void zipFileNoBuffer() throws Exception{
        File zipFile = new File(zipName);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < count; i++) {
            InputStream input = new FileInputStream(sourcePath + sourceName);
            String[] split = sourceName.split("\\.");
            zipOut.putNextEntry(new ZipEntry(sourcePath + split[0] + i + "." + split[1]));
            int temp = 0;
            while ((temp = input.read()) != -1) {
                zipOut.write(temp);
            }
            input.close();
        }
        zipOut.close();
    }

}
