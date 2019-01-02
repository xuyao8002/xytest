package com.xuyao.test.other.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

public class GuavaBloomFilter {

    private static int count = 10000000;

    private static long start;

    private static long startMemory;

    public static void main(String[] args) {
//        test();
        test1();
    }

    public static void test(){
        start = System.currentTimeMillis();
        startMemory = Runtime.getRuntime().totalMemory();
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), count, 0.0001);
        for (int i = 0; i < count; i++) {
            filter.put(i);
        }
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(10000));
        System.out.println(filter.mightContain(1000000));
        System.out.println(filter.mightContain(10000000));
        System.out.println(Runtime.getRuntime().totalMemory() - startMemory);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void test1(){
        start = System.currentTimeMillis();
        startMemory = Runtime.getRuntime().totalMemory();
        BloomFilter<String> filter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(String s, PrimitiveSink primitiveSink) {
                primitiveSink.putString(s, Charset.forName("UTF-8"));
            }
        }, count,0.0001);
        for(int i = 0; i < count; i++){
            filter.put("xy"+i);
        }
        System.out.println(filter.mightContain("xy1"));
        System.out.println(filter.mightContain("xy10000"));
        System.out.println(filter.mightContain("xy1000000"));
        System.out.println(filter.mightContain("xy10000000"));
        System.out.println(Runtime.getRuntime().totalMemory() - startMemory);
        System.out.println(System.currentTimeMillis() - start);
    }
}
