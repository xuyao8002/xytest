package com.xuyao.test;

import java.util.List;

public class MyTest {

    private static int[] arr = {3,4,99,1,5,2,7,9,33,12,77};
    public static void main(String[] args) throws InterruptedException {

        String[] strs = {"my", "name", "is", "xu", "yao"};
        long s = System.nanoTime();
        for(String temp : strs){
            char[] chars = temp.toCharArray();
            chars[0] = (char) (chars[0] - 32);
            System.out.print(new String(chars));
        }
        long e = System.nanoTime();
        System.out.println(e - s);

        s = System.nanoTime();
        for(String temp : strs){
            temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
            System.out.print(temp);
        }
        e = System.nanoTime();
        System.out.println(e - s);

        s = System.nanoTime();
        for(String temp : strs){
            temp = (char)(temp.toCharArray()[0] - 32) + temp.substring(1);
            System.out.print(temp);
        }
        e = System.nanoTime();
        System.out.println(e - s);

        //print(list);
    }

    public static void print(List<String> list){
        for(String ss : list){
            System.out.println(ss);
        }
    }
}
