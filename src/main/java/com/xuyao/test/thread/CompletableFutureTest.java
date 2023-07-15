package com.xuyao.test.thread;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        allOf();
//        compose();
        combine();
    }

    private static void combine() throws InterruptedException, ExecutionException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "World";
        });
        CompletableFuture<String> combine = f1.thenCombine(f2, (s, s2) -> s + " " + s2);
        System.out.println(combine.get());
    }

    private static void compose() throws InterruptedException, ExecutionException {
        CompletableFuture<String> compose = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        }).thenCompose(hello -> CompletableFuture.supplyAsync(() -> hello + " World"));
        System.out.println(compose.get());
    }

    private static void allOf() throws InterruptedException {
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
    }

}
