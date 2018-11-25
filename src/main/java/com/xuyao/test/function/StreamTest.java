package com.xuyao.test.function;

import java.util.stream.LongStream;

public class StreamTest {

    public static void main(String[] args) {
        long sumEnd = 10000000000L;
        long start = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, sumEnd).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+"  time: " + (end - start));
    }
}
