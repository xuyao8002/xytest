package com.xuyao.test.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> {

                    try {
                        System.out.println(df.get().parse("2018-05-01 13:59:58"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

            });
        }
        executorService.shutdown();
        while(true){
            if (executorService.isTerminated()) break;
        }
    }

}
