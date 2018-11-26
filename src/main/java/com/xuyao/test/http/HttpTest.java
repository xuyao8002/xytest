package com.xuyao.test.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest {

    public static InputStream getInputStream(String fileUrl) throws IOException {
        org.apache.http.client.HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(fileUrl);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }

    public static ByteArrayOutputStream getByteArrayOutputStream(String imgURL) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL url = new URL(imgURL);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(data)) != -1) {
                stream.write(data, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

    public static String getBase64StrFromUrl(String imgURL) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL url = new URL(imgURL);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(data)) != -1) {
                stream.write(data, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(stream.toByteArray());
    }
    public static String getImageStrFromUrl(String imgURL) {
        if(StringUtils.isBlank(imgURL)) return "";
        String suffix = imgURL.substring(imgURL.lastIndexOf(".") + 1);
        return getData(suffix) + getBase64StrFromUrl(imgURL);
    }

    public static String getData(String suffix) {
        String data = "data:image/jpeg;base64,";
        if(suffix.equals("gif")) {
            data = "data:image/gif;base64,";
        }else if(suffix.equals("png")) {
            data = "data:image/png;base64,";
        }
        return data;
    }
}
