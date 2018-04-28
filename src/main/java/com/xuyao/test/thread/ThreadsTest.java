package com.xuyao.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsTest
{
    int index = 10000;
    ExecutorService es = Executors.newFixedThreadPool(10);
    int count = 0;
    public static void main(String[] args){
        ThreadsTest test = new ThreadsTest();
        long b = System.currentTimeMillis();
        //test.sync(b);
        //test.atomic(b);
        test.atomic1(b);
    }

    public void sync(long b){
        for(int i = 0; i < index; i++){
            es.execute(new Runnable(){
                @Override
                public void run() {
                    synchronized (es){
                        count++;
                    }
                }
            });
        }
        es.shutdown();
        while(true){
            if(es.isTerminated()){
                System.out.print(count);
                System.out.println(" sycn end: " + (System.currentTimeMillis() - b));
                break;
            }else{
                    /*try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
            }
        }
    }

    public void atomic(long b){
        final AtomicInteger ai = new AtomicInteger(1);
        for(int i = 0; i < index; i++){
            es.execute(new Runnable(){
                @Override
                public void run() {
                    count =  ai.getAndIncrement();
                }
            });
        }
        es.shutdown();
        while(true){
            if(es.isTerminated()){
                System.out.print(count);
                System.out.println(" atomic end: " + (System.currentTimeMillis() - b));
                break;
            }else{
                    /*try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
            }
        }
    }
    private CountDownLatch latch = new CountDownLatch(10000);
    public void atomic1(long b){
        final AtomicInteger ai = new AtomicInteger(1);
        for(int i = 0; i < index; i++){
            es.execute(new Runnable(){
                @Override
                public void run() {
                    count =  ai.getAndIncrement();
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
            System.out.print(count);
            System.out.println(" atomic1 end: " + (System.currentTimeMillis() - b));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}
