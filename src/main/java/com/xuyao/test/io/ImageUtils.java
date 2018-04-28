package com.xuyao.test.io;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

public class ImageUtils {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://111.231.89.17/image/docker.png");
            URLConnection urlConn = url.openConnection();
            System.out.println(urlConn.getContentType());
            BufferedImage read = ImageIO.read(new URL("http://111.231.89.17/image/docker.png"));
            InputStream inputStream = url.openStream();

            byte[] b = new byte[1024];
            int reads = 0;
            FileOutputStream f = new FileOutputStream(new File("C:\\Users\\xuyao\\Desktop\\"+"a.gif"));
            while((reads = inputStream.read(b)) != -1){
                f.write(b, 0, reads);
            }
            f.close();
            System.out.println(inputStream);
            ImageInputStream imageInputStream = new MemoryCacheImageInputStream(inputStream);//ImageIO.createImageInputStream(inputStream);
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
            ImageReader next = imageReaders.next();
            System.out.println(next.getFormatName());
            //ImageIO.write(read, "png", new File("C:\\Users\\xuyao\\Desktop\\d.png"));
//            ImageReader ir = new ImageReader("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
