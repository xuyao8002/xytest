package com.xuyao.test.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(9, () ->{
            System.out.println("-^_^-"); //最后一个线程执行完await后触发
        });
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for(int i = 0; i < 9; i++){
            final int j = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(j);
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        executorService.shutdown();
        System.out.println("-0.0-");
    }
}
