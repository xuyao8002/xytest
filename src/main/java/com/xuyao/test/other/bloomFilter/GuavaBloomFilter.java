package com.xuyao.test.other.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class GuavaBloomFilter {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 10000000, 0.01);
        int c = 10000000;
        for (int i = 0; i < c; i++) {
            filter.put(i);
        }
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(10000));
        System.out.println(filter.mightContain(1000000));
        System.out.println(filter.mightContain(10000000));
        System.out.println(System.currentTimeMillis() - start);
    }
}
