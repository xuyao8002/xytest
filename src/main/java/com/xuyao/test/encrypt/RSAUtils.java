package com.xuyao.test.encrypt;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtils {

    private static final String CHARSET = "UTF-8";

    private static final String ALGORITHM = "RSA";

    public static void main(String[] args) throws Exception {

        String pubKeyStr = "公钥pem文件内容";
        String priKeyStr = "私钥pem文件pkcs8格式内容";

        String data = "";
        //公钥
        PublicKey publicKey=getPublicKey(pubKeyStr);

        //私钥
        PrivateKey privateKey=getPrivateKey(priKeyStr);
        //加密
        byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);
        System.out.println("加密后："+new String(encryptedBytes));
        //解密
        byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);
        System.out.println("解密后："+new String(decryptedBytes));

    }

    private static String decrypt(String content, PrivateKey privateKey) throws Exception {
        return new String(decrypt(Base64.getDecoder().decode(content), privateKey));
    }

    public static byte[] decrypt(String content, String key) throws Exception{
        PrivateKey privateKey = getPrivateKey(key);
        return decrypt(Base64.getDecoder().decode(content), privateKey);
    }

    public static PublicKey getPublicKey(String publicKeyStr) throws Exception{
        byte[] keyBytes= Base64.getDecoder().decode(publicKeyStr.getBytes(CHARSET));
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory= KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }


    public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception{
        byte[] keyBytes=Base64.getDecoder().decode(privateKeyStr.getBytes(CHARSET));
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }


    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }


    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }


    public static void generateKeyPairs() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        System.out.println(Base64Util.encode1(keyPair.getPublic().getEncoded()));
        System.out.println(Base64Util.encode1(keyPair.getPrivate().getEncoded()));
    }

}
