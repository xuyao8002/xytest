package com.xuyao.test.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageTest {

    public static void main(String[] args) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL url = new URL("http://xxxx/image/docker.png");
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(5 * 1000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len;
            while ((len = is.read(data)) != -1) {
                stream.write(data, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = stream.toByteArray();
        System.out.println(bytes.length);

    }


}
