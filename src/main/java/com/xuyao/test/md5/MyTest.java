package com.xuyao.test.md5;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MyTest extends Thread {
    private static Integer w = 0;

    public static void main(String[] args) throws Exception {
        String str = "admin";
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        System.out.println(encoder.encodePassword(str, null));


        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(str.getBytes());
        byte[] digest = md.digest();
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        String en = new BigInteger(1, digest).toString(16);
        System.out.println(en);
        System.out.println(digest.length);
        for (byte b : digest) {
            System.out.print(b + ", ");
        }

    }

}
