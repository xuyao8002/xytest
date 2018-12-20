package com.xuyao.test.other.bloomFilter;

import com.xuyao.test.encrypt.hash.HashUtil;

public class BloomFilter {

    private static int size = 10000000;
    private static int[] array = new int[size];

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            put(i);
        }
        System.out.println(contain(1));
        System.out.println(contain(10000));
        System.out.println(contain(1000000));
        System.out.println(contain(10000000));
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void put(int i){
        String v = String.valueOf(i);
        int hash1 = HashUtil.DEKHash(v);
        int hash2 = HashUtil.DJBHash(v);
        int hash3 = HashUtil.SDBMHash(v);
        array[hash1 % size] = 1;
        array[hash2 % size] = 1;
        array[hash3 % size] = 1;
    }

    public static boolean contain(int i){
        String v = String.valueOf(i);
        int hash1 = HashUtil.DEKHash(v);
        int hash2 = HashUtil.DJBHash(v);
        int hash3 = HashUtil.SDBMHash(v);
        if(array[hash1 % size] == 0) return false;
        if(array[hash2 % size] == 0) return false;
        if(array[hash3 % size] == 0) return false;
        return true;
    }

}
