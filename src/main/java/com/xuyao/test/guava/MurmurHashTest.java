package com.xuyao.test.guava;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;


public class MurmurHashTest {

    public static void main(String[] args) {
        String name = "xuyao";
        String name1 = "xuyao1";
        String name2 = "xuyao2";
        HashFunction f = Hashing.murmur3_32();
        HashCode xuyao = f.hashString(name, Charset.forName("UTF-8"));
        HashCode xuyao1 = f.hashString(name1, Charset.forName("UTF-8"));
        HashCode xuyao2 = f.hashString(name2, Charset.forName("UTF-8"));
        System.out.println(xuyao.asInt());
        System.out.println(xuyao1.asInt());
        System.out.println(xuyao2.asInt());
        System.out.println("-------------------------------------------------");
        System.out.println(name.hashCode());
        System.out.println(name1.hashCode());
        System.out.println(name2.hashCode());

    }
}
