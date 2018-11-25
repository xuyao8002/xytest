package com.xuyao.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        ExecutorService es = Executors.newFixedThreadPool(10);
        AtomicInteger integer = new AtomicInteger(0);
        for(int i = 0; i < 100; i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    integer.incrementAndGet();
                    latch.countDown();
                }
            });
        }
        latch.await();
        System.out.println(integer.get());
        es.shutdown();
    }
}
