package com.xuyao.test.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUtils {

    public static void copyImage(String imageUrl, String path){
        URL url = null;
        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        try {
            url = new URL(imageUrl);
            InputStream inputStream = url.openStream();
            byte[] b = new byte[1024];
            int reads = 0;
            FileOutputStream f = new FileOutputStream(new File(path + imageName));
            while((reads = inputStream.read(b)) != -1){
                f.write(b, 0, reads);
            }
            f.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean copyImage1(String imageUrl, String path){
        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        String suffix = imageName.substring(imageName.lastIndexOf(".") + 1);
        boolean flag = false;
        try {
            BufferedImage read = ImageIO.read(new URL(imageUrl));
            flag = ImageIO.write(read, suffix, new File(path + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
