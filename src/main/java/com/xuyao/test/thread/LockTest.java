package com.xuyao.test.thread;

import java.util.concurrent.locks.LockSupport;

public class LockTest {
    static ILock lock = new Lock();
    public static void main(String[] args) throws InterruptedException {

        lock.lock();
        System.out.println("lock");
        add();
        lock.unlock();
    }

    public static void add() throws InterruptedException {
        lock.lock();
        System.out.println("relock");
        lock.unlock();

    }

    interface ILock{
        void lock() throws InterruptedException;
        void unlock();
    }

    //不可重入锁
    static class Lock implements ILock{
        private boolean isLock;

        @Override
        public void lock() throws InterruptedException {
            while(isLock){
                LockSupport.park();
            }
            isLock = true;
        }

        @Override
        public void unlock(){
            isLock = false;
            LockSupport.unpark(Thread.currentThread());
        }
    }

    //可重入锁
    static class RnLock implements ILock{
        private boolean isLock;
        private int count;
        private Thread cur;

        @Override
        public void lock() throws InterruptedException {
            Thread thread = Thread.currentThread();
            while(isLock && thread != cur){
                LockSupport.park();
            }
            count++;
            isLock = true;
            cur = thread;
        }

        @Override
        public void unlock(){
            if(cur == Thread.currentThread()){
                count--;
                if(count == 0){
                    isLock = false;
                    LockSupport.unpark(cur);
                }
            }
        }
    }

}
