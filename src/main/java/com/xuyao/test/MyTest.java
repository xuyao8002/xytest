package com.xuyao.test;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class MyTest {
    public static void main(String[] args) throws IOException {
        String fileUrl = "http://xxx/image/Elephant.jpg";
HttpGet get = new HttpGet(fileUrl);
        CloseableHttpResponse response1 = null;
        InputStream content = null;
        byte[] bytes = new byte[10240];
        try {
        CloseableHttpClient client = HttpClients.createDefault();
        response1 = client.execute(get);
        HttpEntity entity1 = response1.getEntity();
        content = entity1.getContent();
        int count = 0;
        int all = 0;
        while((count = content.read(bytes)) > -1){
            System.out.println(count);
            all += count;
        }
//            bytes = new byte[content.available()];

            System.out.println("all: " + all);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response1.close();
        }

    }


}

