package com.xuyao.test.encrypt.rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtils {

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

    public static PublicKey getPublicKey(String publicKeyStr) throws Exception{
        byte[] keyBytes= Base64.getDecoder().decode(publicKeyStr.getBytes("UTF-8"));
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }


    public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception{
        byte[] keyBytes=Base64.getDecoder().decode(privateKeyStr.getBytes("UTF-8"));
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }


    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }


    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }


}
