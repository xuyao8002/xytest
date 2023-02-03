package com.xuyao.test.other;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyWallpaper {

    public static void main(String[] args) throws IOException {
        String userPath = "C:\\Users\\xuyao\\";
        File user = new File(userPath);
        if (!user.exists()) {
            userPath = "C:\\Users\\Administrator\\";
            user = new File(userPath);
            if (!user.exists()) {
                System.err.println("未找到用户");
                return;
            }
        }
        String path = userPath + "AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";
        String output = userPath + "Desktop";
        File folder = new File(path);
        if(folder.isDirectory()){
            File[] files = folder.listFiles();
            if(files == null || files.length == 0) return;
            File outputDir = new File(output + "\\output");
            if(!outputDir.exists()) outputDir.mkdir();
            BufferedImage bi;
            int height;
            int width;
            FileChannel in;
            FileChannel out;
            File outFile;
            for(File file : files){
                bi = ImageIO.read(file);
                height = bi.getHeight();
                width = bi.getWidth();
                if(height != 1080 && width != 1920) continue;
                System.out.println(file.getName() + ", 高：" + bi.getHeight() + ", 宽：" + bi.getWidth());
                in = new FileInputStream(file).getChannel();
                out = new FileOutputStream(new File(outputDir + "\\" + file.getName() + ".jpg")).getChannel();
                in.transferTo(0, in.size(), out);
                //file.renameTo(new File(outputDir + file.getName() + ".jpg"));
            }
        }
    }
}
