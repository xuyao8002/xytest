package com.xuyao.test.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        long[] times = {5000L, 4000L, 3000L, 8000L, 1000L, 1000L, 1000L, 1000L};
        List<Future<Long>> futureList = new ArrayList<>();
        for (long time : times) {
            Future<Long> submit = executorService.submit(() -> {
                System.out.println("time: " + time);
                Thread.sleep(time);
                return time;
            });
            futureList.add(submit);
        }
//        for (Future<Long> longFuture : futureList) {
//            System.out.println(longFuture.get());
//        }
        int c = 0;
        while(true){
            Iterator<Future<Long>> iterator = futureList.iterator();
            while(iterator.hasNext()){
                Future<Long> next = iterator.next();
                if(next.isDone()){
                    System.out.println(next.get());
                    c++;
                    iterator.remove();
                }
            }
            if(c == times.length){
                break;
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

}

