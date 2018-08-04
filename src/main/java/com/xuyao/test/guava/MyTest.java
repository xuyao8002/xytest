package com.xuyao.test.guava;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        String[] array = {"01","11","22","33"};

        List<Integer> list = Lists.transform(Arrays.asList(array), Integer::valueOf);
    }



}
