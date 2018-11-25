package com.xuyao.test.thread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args){
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " up");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " up1");
        });
        thread.start();
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
