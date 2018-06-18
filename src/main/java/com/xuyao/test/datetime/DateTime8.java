package com.xuyao.test.datetime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTime8 {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        System.out.println(localDateTime.minus(2, ChronoUnit.DAYS));

        LocalDateTime old = LocalDateTime.of(2017, 11, 3, 10, 11);
        System.out.println(old + ", " + old.plus(180, ChronoUnit.DAYS));

        System.out.println(old.withYear(2018));

    }
}
