package com.xuyao.test.thread;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        Random random = new Random();
        int count = 5;
        for (int i = 0; i < count; i++) {
            completionService.submit(() -> {
                int sleep = random.nextInt(count);
                String thread = Thread.currentThread().getName();
                System.out.println(thread + ", will sleep: " + sleep);
                TimeUnit.SECONDS.sleep(sleep);
                return thread + ", sleep: " + sleep;
            });
        }
        for (int i = 0; i < count; i++) {
            System.out.println(completionService.take().get());
        }
        executorService.shutdown();
    }
}
