package com.xuyao.test.function;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StreamTest {

    private static void listToMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("name", "xy");
        m1.put("age", "1");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("name", "xy");
        m2.put("age", "2");
        Map<String, Object> m3 = new HashMap<>();
        m3.put("name", "xu");
        m3.put("age", "3");
        list.add(m1);
        list.add(m2);
        list.add(m3);
        LinkedHashMap<Object, List<Map<String, Object>>> map = list
                .stream()
                .collect(Collectors.groupingBy(m -> m.get("name"), LinkedHashMap::new, Collectors.toList()));
        map.forEach((x,y) -> System.out.println(x + "," + y));
    }

    private static void parallel() {
        long sumEnd = 10000000000L;
        long start = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, sumEnd).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+"  time: " + (end - start));
    }
}
