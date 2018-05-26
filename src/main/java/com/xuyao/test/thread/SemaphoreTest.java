package com.xuyao.test.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {


    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(6);
        Semaphore semaphore = new Semaphore(4);
        for(int i = 0; i < 6; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + ": start");
                        Thread.sleep(1000L);
                        System.out.println(Thread.currentThread().getName() + ": end");
                        semaphore.release();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
es.shutdown();
    }

}
