package com.xuyao.test.io;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class InputStreamToString {

    public static void main(String[] args) throws IOException {
        String encoding = "UTF-8";
        String pathname = "";
        InputStream stream = new FileInputStream(new File(pathname));
        String content;
//        content = byIOUtils(encoding, stream);
        content = byByteArrayOutputStream(encoding, stream);
        System.out.println(content);
    }

    private static String byByteArrayOutputStream(String encoding, InputStream stream) throws IOException {
        String content;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        while((length = stream.read(bytes)) != -1){
            outputStream.write(bytes, 0, length);
        }
        content = outputStream.toString(encoding);
        return content;
    }

    private static String byIOUtils(String encoding, InputStream stream) throws IOException {
        return IOUtils.toString(stream, encoding);
    }

}
