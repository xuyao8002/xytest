package com.xuyao.test.thread;


import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> f = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000L);
                System.out.println("5000L");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000L);
                System.out.println("4000L");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000L);
                System.out.println("2000L");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> fa = CompletableFuture.allOf(f, f1, f2);
        while (!fa.isDone()) {
            System.out.println("wait: " + System.currentTimeMillis());
            Thread.sleep(1000L);
        }
//        ff.get();
        System.out.println("end");
    }

}
