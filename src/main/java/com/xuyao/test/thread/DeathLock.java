package com.xuyao.test.thread;

import java.util.concurrent.TimeUnit;

public class DeathLock {
    public static void main(String[] args) throws InterruptedException {
            Integer i = 0;
            Integer j = 1;
            Thread thread = new Thread(() -> {
                synchronized (i){
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (j){

                        System.out.println("i * j = " + (i * j));
                    }
                }
            });
            thread.start();
            TimeUnit.SECONDS.sleep(1);
            Thread thread1 = new Thread(() -> {
                synchronized (j){
                    synchronized (i){
                        System.out.println("i * j = " + (i * j));
                    }
                }
            });
            thread1.start();


    }
}
