package com.xuyao.test.other.distributedLocks.zooKeeper;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class MutexLock {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();

        InterProcessMutex mutex = new InterProcessMutex(client, "/mutex/lock");
        mutex.acquire();

        System.out.println("mutex lock");

        mutex.release();
        client.close();
    }

}
