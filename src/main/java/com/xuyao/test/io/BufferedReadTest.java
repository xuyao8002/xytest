package com.xuyao.test.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class BufferedReadTest {

private static String filePath = "E:\\temp.txt";
    public static void main(String[] args) throws Exception {
        read4();
    }

    public static void read() throws Exception {
        int i = 1;
        LineIterator it = FileUtils.lineIterator(new File(filePath), "UTF-8");
        while (it.hasNext()) {
            System.out.println(i++ + ", " + it.nextLine());
        }
    }

    public static void read1() throws Exception {
        FileInputStream is = new FileInputStream(filePath);
        Scanner sc = new Scanner(is, "UTF-8");
        int i = 1;
        while (sc.hasNextLine()) {
            System.out.println(i++ + ", " + sc.nextLine());
        }
    }

    public static void read2() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath), 1*1024*1024);
        String line;
        int i = 1;
        while ((line = br.readLine()) != null) {
            System.out.println(i + ", " + line);
            i++;
        }
    }

    public static void read3() throws Exception {
        try (LineIterator iterator = IOUtils.lineIterator(new FileInputStream(filePath), "UTF-8")) {
            int i = 1;
            while (iterator.hasNext()) {
                String line=iterator.nextLine();
                System.out.println(i++ + ", " + line);
            }
        }
    }

    public static void read4() throws Exception {
        Files.lines(Paths.get(filePath), Charset.forName("UTF-8")).forEach(line -> {
            System.out.println(line);
        });
    }

}

