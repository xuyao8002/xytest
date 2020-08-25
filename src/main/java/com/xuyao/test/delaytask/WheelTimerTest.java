package com.xuyao.test.delaytask;


import io.netty.util.HashedWheelTimer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WheelTimerTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
        hashedWheelTimer.newTimeout(timeout -> {
            latch.countDown();
            System.out.println("d, " + System.currentTimeMillis());
        }, 4, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            latch.countDown();
            System.out.println("b, " + System.currentTimeMillis());
        }, 2, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            latch.countDown();
            System.out.println("c, " + System.currentTimeMillis());
        }, 3, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            latch.countDown();
            System.out.println("a, " + System.currentTimeMillis());
        }, 1, TimeUnit.SECONDS);
        latch.await();
        hashedWheelTimer.stop();
    }
}
