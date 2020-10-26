package com.xuyao.test.thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadInitTest {

    private static List<Integer> list = new ArrayList<>();
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        int i = 6;
        ExecutorService executorService = Executors.newFixedThreadPool(i);
        for (int i1 = 0; i1 < i; i1++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread());
                if(list.isEmpty()){
                    lock.lock();
                    try{
                        if(list.isEmpty()){
                            init();
                        }
                    }finally {
                        lock.unlock();
                    }

                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(2, TimeUnit.SECONDS);
    }

    public static void init(){
        System.out.println(Thread.currentThread() + "-----------------------init-----------------------------");
        int i = 10;
        List<Integer> temp = new ArrayList<>();
        for (int i1 = 0; i1 < i; i1++) {
            temp.add(i);
        }
        list = temp;
    }

}



