package com.xuyao.test.other;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerData {

    public static void main(String[] args) {
        Faker fakerWithCN = new Faker(Locale.CHINA);
        System.out.println(fakerWithCN.name().fullName());
        System.out.println(fakerWithCN.phoneNumber().cellPhone());
        System.out.println(fakerWithCN.phoneNumber().phoneNumber());
        System.out.println(fakerWithCN.address().cityName());
        System.out.println(fakerWithCN.address().streetAddress());
        System.out.println(fakerWithCN.university().name());
    }

}
