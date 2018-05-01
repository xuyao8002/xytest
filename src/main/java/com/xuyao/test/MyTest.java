package com.xuyao.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ThreadLocal<DateFormat> df1 = new ThreadLocal<DateFormat>(){

            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };
        for(int i = 0; i < 50; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(df1.get().parse("2018-05-01 13:59:58"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

}
