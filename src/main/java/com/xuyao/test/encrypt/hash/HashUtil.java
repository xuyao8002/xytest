package com.xuyao.test.encrypt.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class HashUtil {

    private static int prime = 0x8765fed1;

    /**
     * 加法hash
     *
     * @param key   字符串
     * @param prime 一个质数
     * @return hash结果
     */
    public static int additiveHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }

    /**
     * 旋转hash
     *
     * @param key   输入字符串
     * @param prime 质数
     * @return hash值
     */
    public static int rotatingHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); ++i)
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        return (hash % prime);
        // return (hash ^ (hash>>10) ^ (hash>>20));
    }
    // 替代：
    // 使用：hash = (hash ^ (hash>>10) ^ (hash>>20)) & mask;
    // 替代：hash %= prime;

    /**
     * 一次一个hash
     */
    public static int oneByOneHash(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        // return (hash & M_MASK);
        return hash;
    }


    public static int bernstein(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i)
            hash = 33 * hash + key.charAt(i);
        return hash;
    }


    /**
     * 改进的32位FNV算法1
     */
    public static int FNVHash1(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++)
            hash = (hash ^ data.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }


    /**
     * RS算法hash
     */
    public static int RSHash(String str) {
        int b = 378551;
        int a = 63689;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * JS算法
     */
    public static int JSHash(String str) {
        int hash = 1315423911;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * PJW算法
     */
    public static int PJWHash(String str) {
        int BitsInUnsignedInt = 32;
        int ThreeQuarters = (BitsInUnsignedInt * 3) / 4;
        int OneEighth = BitsInUnsignedInt / 8;
        int HighBits = 0xFFFFFFFF << (BitsInUnsignedInt - OneEighth);
        int hash = 0;
        int test;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << OneEighth) + str.charAt(i);

            if ((test = hash & HighBits) != 0) {
                hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
            }
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * ELF算法
     */
    public static int ELFHash(String str) {
        int hash = 0;
        int x;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 4) + str.charAt(i);
            if ((x = (int) (hash & 0xF0000000L)) != 0) {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * BKDR算法
     */
    public static int BKDRHash(String str) {
        int seed = 131; // 31 131 1313 13131 131313 etc..
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * seed) + str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * SDBM算法
     */
    public static int SDBMHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * DJB算法
     */
    public static int DJBHash(String str) {
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * DEK算法
     */
    public static int DEKHash(String str) {
        int hash = str.length();
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * AP算法
     */
    public static int APHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ((i & 1) == 0) ? ((hash << 7) ^ str.charAt(i) ^ (hash >> 3))
                    : (~((hash << 11) ^ str.charAt(i) ^ (hash >> 5)));
        }
        // return (hash & 0x7FFFFFFF);
        return hash;
    }

    public static int javaHash(String str) {
        int hash = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            hash = 31 * hash + str.charAt(i);
        }
        return hash;
    }

    /**
     * 混合hash算法，输出64位的值
     */
    public static long mixHash(String str) {
        long hash = str.hashCode();
        hash <<= 32;
        hash |= FNVHash1(str);
        return hash;
    }

    /**
     * murmurHash 随机分布性较好
     * @param str
     * @return
     */
    public static int murmurHash(String str){
        HashFunction function = Hashing.murmur3_32();
        HashCode hc = function.hashString(str, Charset.forName("UTF-8"));
        return hc.asInt();
    }

}
