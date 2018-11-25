package com.xuyao.test.number;


import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MyTest {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        BigDecimal b = new BigDecimal("9.989034");
        b = b.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(b.toString());
long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        BigDecimal b1 = new BigDecimal("9.989034");
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(b1);
        System.out.println(format);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

