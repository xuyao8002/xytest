package com.xuyao.test.io;

import com.github.kilianB.hash.Hash;
import com.github.kilianB.hashAlgorithms.AverageHash;
import com.github.kilianB.hashAlgorithms.HashingAlgorithm;
import com.github.kilianB.hashAlgorithms.PerceptiveHash;
import com.github.kilianB.matcher.exotic.SingleImageMatcher;

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

    public static boolean checkSimilarity(String url, String url1){
        try{
            BufferedImage img = ImageIO.read(new URL(url));
            BufferedImage img1 = ImageIO.read(new URL(url1));
            HashingAlgorithm hasher = new PerceptiveHash(32);
            Hash hash = hasher.hash(img);
            Hash hash1 = hasher.hash(img1);
            double similarityScore = hash.normalizedHammingDistance(hash1);
            if(similarityScore < .2) {
                return true;
            }
            //Chaining multiple matcher for single image comparison
            SingleImageMatcher matcher = new SingleImageMatcher();
            matcher.addHashingAlgorithm(new AverageHash(64),.3);
            matcher.addHashingAlgorithm(new PerceptiveHash(32),.2);
            return matcher.checkSimilarity(img,img1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
