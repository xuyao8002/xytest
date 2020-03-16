package com.xuyao.test.other;

import java.util.ArrayList;
import java.util.List;

//PECS（Producer Extends Consumer Super）
public class Generic {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(0);

        //上界通配符，用于获取元素，无法添加
        List<? extends Number> list = integers;
//        list.add(1); 编码报错
        final Number number = list.get(0);

        List<Number> longs = new ArrayList<>();
        longs.add(6L);
        //下界通配符，用于添加元素，获取丢失具体类型，只能是Object
        List<? super Number> list1 = longs;
        list1.add(3.333D);

    }

}
