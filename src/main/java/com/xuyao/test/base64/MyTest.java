package com.xuyao.test.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64;

public class MyTest {

    private static String str = "Hello, World!";
    private static final String CHARSET = "UTF-8";
    public static String encode(String str) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String en = encoder.encode(str.getBytes(CHARSET));
        System.out.println(en);
        return en;
    }

    public static String decode(String str) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        String de = new String(decoder.decodeBuffer(str), CHARSET);
        System.out.println(de);
        return de;
    }

    //1.8
    public static String encode1(String str) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String en = encoder.encodeToString(str.getBytes(CHARSET));
        System.out.println(en);
        return en;
    }

    //1.8
    public static String decode1(String str) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        String de = new String(decoder.decode(str), CHARSET);
        System.out.println(de);
        return de;
    }

    public static String encode2(String str) throws Exception {
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        String en = base64.encodeToString(str.getBytes(CHARSET));
        System.out.println(en);
        return en;
    }

    public static String decode2(String str) throws Exception {
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        String de = new String(base64.decode(str), CHARSET);
        System.out.println(de);
        return de;
    }

    public static void main(String[] args) throws Exception {
//        encode(str);
        long b = System.currentTimeMillis();
        decode(encode(str));
        System.out.println(System.currentTimeMillis() - b);

        b = System.currentTimeMillis();
        decode1(encode1(str));
        System.out.println(System.currentTimeMillis() - b);

        b = System.currentTimeMillis();
        decode2(encode2(str));
        System.out.println(System.currentTimeMillis() - b);
    }
}
