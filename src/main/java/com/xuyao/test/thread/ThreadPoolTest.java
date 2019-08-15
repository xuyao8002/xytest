package com.xuyao.test.thread;


import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {


    @Test
    public void test(){
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
        ExecutorService executorService = new ThreadPoolExecutor(poolSize, poolSize,
                0, TimeUnit.SECONDS, queue, policy);
        Future<?> submit = executorService.submit(() -> {
            Object obj = null;
            System.out.println(obj.toString());
        });
        try {
            String o = (String) submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.execute(() -> {
            Object obj = null;
            System.out.println(obj.toString());
        });
    }
}
