package com.xuyao.test.number;


import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalTest {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        BigDecimal b = new BigDecimal("9.8990340");
        b = b.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(b.toString());
        //去除尾部0
        b = b.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        System.out.println(b.toString());
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        BigDecimal b1 = new BigDecimal("9.899034");
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(b1);
        System.out.println(format);
        end = System.currentTimeMillis();
        System.out.println(end - start);

        //比较两个值是否相等应用compareTo
        System.out.println(b.compareTo(b1));
        System.out.println(b.equals(b1));
    }
}

