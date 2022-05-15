package com.xuyao.test.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {
//        aba();
        correct();
    }

    private static void aba() {
        AtomicInteger integer = new AtomicInteger(0);
        new Thread(() -> {
            integer.compareAndSet(0, 1);
            integer.compareAndSet(1, 0);
            System.out.println(Thread.currentThread().getName()+": " + integer.get());
            System.out.println();
        }, "thread0").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                integer.compareAndSet(0, 2);
                System.out.println(Thread.currentThread().getName()+": " + integer.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1").start();
    }

    private static void correct() {
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(0, 0);
        int stamp = reference.getStamp();
        new Thread(() -> {
            reference.compareAndSet(0, 1, reference.getStamp(), reference.getStamp() +1);
            reference.compareAndSet(1, 0, reference.getStamp(), reference.getStamp() +1);
            System.out.println(String.format("线程：%s，值：%d，stamp：%d", Thread.currentThread().getName(), reference.getReference(), reference.getStamp()));
        }, "thread0").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                boolean b = reference.compareAndSet(0, 2, stamp, stamp + 1);
                System.out.println(String.format("线程：%s，修改结果：%s, 值：%d，stamp：%d", Thread.currentThread().getName(), b, reference.getReference(), stamp));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1").start();
    }

}
