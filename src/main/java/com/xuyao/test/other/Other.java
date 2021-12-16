package com.xuyao.test.other;

import java.nio.charset.Charset;
import java.util.Objects;

public class Other {
    public static void main(String[] args) {
        System.out.println("Os Name: " + System.getProperty("os.name"));
        System.out.println("Line Separator is \\r\\n: " + Objects.equals("\r\n", System.getProperty("line.separator")));
        System.out.println("Line Separator is \\n: " + Objects.equals("\n", System.getProperty("line.separator")));
        System.out.println("Line Separator is \\r: " + Objects.equals("\r", System.getProperty("line.separator")));
        System.out.println("Default Charset: " + Charset.defaultCharset());
    }

    public static boolean isLeapYear(int year){
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}