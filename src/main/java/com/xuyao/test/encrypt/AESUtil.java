package com.xuyao.test.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

    //算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 加密
     * @param content 内容
     * @param key 16位
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key) throws Exception {
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes(CHARSET_NAME));
        // base64编码,避免乱码
        return Base64.encodeBase64String(b);
//        return Base64.encodeBase64URLSafeString(b);
    }

    /**
     * 解密
     * @param encrypt 加密后内容
     * @param key 16位
     * @return
     * @throws Exception
     */
    public static String decrypt(String encrypt, String key) throws Exception {
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        // base64编码,避免乱码
        byte[] encryptBytes = Base64.decodeBase64(encrypt);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes, CHARSET_NAME);
    }

    private static Cipher getCipherInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        return Cipher.getInstance(ALGORITHMSTR);
    }

}
