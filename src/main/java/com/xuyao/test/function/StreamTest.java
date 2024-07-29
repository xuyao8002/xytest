package com.xuyao.test.function;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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
        LinkedHashMap<Object, List<Map<String, Object>>> mapList = list
                .stream()
                .collect(Collectors.groupingBy(m -> m.get("name"), LinkedHashMap::new, Collectors.toList()));
        mapList.forEach((x,y) -> System.out.println(x + "," + y));

        //list转map，无重复key
        //Map<Object, Map<String, Object>> map = list.stream().collect(Collectors.toMap(m -> m.get("name"), Function.identity()));
        //Map<Object, Map<String, Object>> map1 = list.stream().collect(Collectors.toMap(m -> m.get("name"), m -> m));
        //list转map，重复key时新值覆盖旧值
        Map<Object, Map<String, Object>> map2 = list.stream().collect(Collectors.toMap(m -> m.get("name"), m -> m, (k1, k2) -> k2));
        System.out.println(map2);

    }

    private static void sum() {
        List<BigDecimal> decimals = new ArrayList<>();
        decimals.add(new BigDecimal("0.01"));
        decimals.add(new BigDecimal("0.99"));
        System.out.println(decimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public static void flatMap(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        List<Integer> nums1 = new ArrayList<>();
        nums1.add(4);
        nums1.add(5);
        nums1.add(6);
        List<Integer> mergedList = Stream.of(nums, nums1).flatMap(List::stream).collect(Collectors.toList());
        System.out.println(mergedList);

        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("1", "2", "3"));
        list.add(Arrays.asList("8", "5", "6"));
        List<String> stringList = list.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        System.out.println(stringList);
        List<Integer> integerList = list.stream().flatMapToInt(e -> e.stream()
                .mapToInt(Integer::parseInt)).boxed().collect(Collectors.toList());
        System.out.println(integerList);


    }

    private static void parallel() {
        long sumEnd = 10000000000L;
        long start = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, sumEnd).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+"  time: " + (end - start));
    }
}
