package com.xuyao.test.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class BufferedReadTest {

private static String filePath = "E:\\bigData.txt";
    public static void main(String[] args) throws Exception {
read1();
    }

    public static void read() throws Exception {
        int i = 1;
        LineIterator it = FileUtils.lineIterator(new File(filePath), "UTF-8");
        while (it.hasNext()) {
            System.out.println(i++ + ", " + it.nextLine());
        }
    }

    public static void read1() throws Exception {
        FileInputStream is = new FileInputStream(new File(filePath));
        Scanner sc = new Scanner(is, "UTF-8");
        int i = 1;
        while (sc.hasNextLine()) {
            System.out.println(i++ + ", " + sc.nextLine());
        }
    }

    public static void read2() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        String line;
        int i = 1;
        while ((line = br.readLine()) != null) {
            System.out.println(i + ", " + line);
            i++;
        }
    }

}

