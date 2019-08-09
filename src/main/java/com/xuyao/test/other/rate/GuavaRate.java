package com.xuyao.test.other.rate;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GuavaRate {
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 10;
        //每秒获取5个
        RateLimiter rateLimiter = RateLimiter.create(5);
        TimeUnit.SECONDS.sleep(1);
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CountDownLatch latch = new CountDownLatch(5);
        for(int i = 0; i < threadNum; i++){
            executorService.execute(() -> {
                latch.countDown();
                if(rateLimiter.tryAcquire()){
                    System.out.println("获取到");
                }else{
                    System.out.println("未获取到");
                }
            });
        }
        latch.await();
        executorService.shutdown();
    }
}
