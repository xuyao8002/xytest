package com.xuyao.test.other.distributedlocks.zookeeper;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutexLock {

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    private String connectString = "localhost:2181";

    private String path = "/mutex/lock";

    private CuratorFramework client;

    private InterProcessMutex mutex;

    public MutexLock() {
        client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
        client.start();
        mutex = new InterProcessMutex(client, path);
    }

    public void acquire() throws Exception {
        mutex.acquire();
    }

    public void release() throws Exception {
        mutex.release();
    }

    public void close(){
        client.close();
    }

    public void mutex() throws Exception {
        int i = 10;
        CountDownLatch latch = new CountDownLatch(i);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i1 = 0; i1 < i; i1++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "start");
                MutexLock mutexLock = new MutexLock();
                try {
                    mutexLock.acquire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ", hello");
                latch.countDown();
                try {
                    mutexLock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "end");
                mutexLock.close();
            });
        }
        latch.await();
        executorService.shutdown();
    }

    public static void main(String[] args) throws Exception {
        MutexLock x = new MutexLock();
        x.mutex();
    }

}
