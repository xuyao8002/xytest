package com.xuyao.test.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FortJoinTest extends RecursiveTask<Long> {

    private Long start;

    private Long end;

    private static final Long THRESHOLD = 10000L;

    public FortJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long length = end - start;
        if(length < THRESHOLD){
            Long sum = 0L;
            for(Long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        }else{
            Long middle = (start + end) / 2;
            FortJoinTest test = new FortJoinTest(start, middle);
            test.fork();
            FortJoinTest test1 = new FortJoinTest(middle + 1, end);
            test1.fork();
            return test.join() + test1.join();
        }
    }

    public static void main(String[] args) {
        Long num = 100000000L;
        long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Long> task = new FortJoinTest(0L,num);//参数为起始值与结束值
        Long invoke = forkJoinPool.invoke(task);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + invoke+"  time: " + (l1-l));
    }
}
