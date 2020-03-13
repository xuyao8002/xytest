package com.xuyao.test.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {

    private static final String CHARSET = "UTF-8";
    public static String encode(String str) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(str.getBytes(CHARSET));
    }

    public static byte[] decode(String str) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(str);
    }

    //1.8
    public static String encode1(String str) throws Exception {
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        return encoder.encodeToString(str.getBytes(CHARSET));
    }

    public static String encode1(byte[] bytes) {
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    //1.8
    public static byte[] decode1(String str) {
        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
        return decoder.decode(str);
    }

    public static String encode2(String str) throws Exception {
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        return base64.encodeToString(str.getBytes(CHARSET));
    }

    public static byte[] decode2(String str) {
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        return base64.decode(str);
    }

}
