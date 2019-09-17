package com.xuyao.test.other.distributedLocks.redis;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class MutexLock {

    private int secondsToExpire = 1000;

    private String host = "10.99.44.141";

    private int port = 6379;

    private JedisPool jedisPool = getJedisPool();

    private String key = "name";

    private String keyPrefix = "mutex_lock_";

    @Test
    public void test() throws InterruptedException {
        long start = System.currentTimeMillis();
        int count = 20;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
        ExecutorService executorService = new ThreadPoolExecutor(count, count,
                0, TimeUnit.SECONDS, queue, policy);
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            String val = String.valueOf(i);
            executorService.execute(() -> {
                boolean lock = lock(key, val);
                if (lock) {
                    System.out.println(Thread.currentThread().getName() + " lock key = " + key + " value = " + val + " success! ");
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unLock(key, val);
                } else {
                    System.err.println(Thread.currentThread().getName() + " lock key = " + key + " value = " + val + " failure! ");
                }
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("all: " + (System.currentTimeMillis() - start));
    }

    private JedisPool getJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(5000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
//        config.setTestWhileIdle(true);
//        config.setBlockWhenExhausted(true);
        return new JedisPool(config, host, port);
    }

    /**
     * 加锁
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = keyPrefix + key;
            if ("OK".equals(jedis.set(key, value, new SetParams().nx().ex(secondsToExpire)))) {
                System.out.println("Reids Lock key : " + key + ",value : " + value);
                return true;
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 加锁
     * @param key
     * @param value
     * @param waitSeconds 最多等待秒数
     * @return
     */
    public boolean lock(String key, String value, long waitSeconds) {
        long endSeconds = System.currentTimeMillis() + waitSeconds * 1000;
        while(true){
            boolean lock = lock(key, value);
            if(lock){
                return true;
            }
            waitSeconds = endSeconds - System.currentTimeMillis();
            if(waitSeconds <= 0){
                return false;
            }
        }
    }

    /**
     * 加锁
     * @param key
     * @param value
     * @param waitTimes 最多等待次数
     * @return
     */
    public boolean lock(String key, String value, int waitTimes) {
        while(true){
            boolean lock = lock(key, value);
            if(lock){
                return true;
            }
            waitTimes--;
            if(waitTimes <= 0){
                return false;
            }
        }
    }

    /**
     * 解锁
     * @param key
     * @param value
     * @return
     */
    public boolean unLock(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = keyPrefix + key;
            String command = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            System.out.println("Reids unLock key : " + key + ",value : " + value);
            if ("1".equals(jedis.eval(command, Collections.singletonList(key), Collections.singletonList(value)))) {
                return true;
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }


}
