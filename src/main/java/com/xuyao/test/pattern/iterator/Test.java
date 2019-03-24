package com.xuyao.test.pattern.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String[] i = {"1","3","3","4","5"};
        MyIteratable<String> arr = new MyArray<>(i);
        MyIterator<String> iterator = arr.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }


        List<Double> list = Arrays.asList(new Double[]{8.8D, 6.6D});
        MyIteratable<Double> ml = new MyList<>(list);
        MyIterator<Double> iterator1 = ml.iterator();
        while (iterator1.hasNext()) {
            Double next = iterator1.next();
            System.out.println(next);
        }
    }

}
