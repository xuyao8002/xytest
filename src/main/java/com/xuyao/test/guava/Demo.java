package com.xuyao.test.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Demo {

    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        System.out.println(multiset.size());
        System.out.println(multiset.count("a"));
        System.out.println("----------");

        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("day", "saturday");
        multimap.put("day", "sunday");
        System.out.println(multimap.get("day"));
        System.out.println("----------");

        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("day", "saturday");
        System.out.println(biMap.inverse().get("saturday"));
        System.out.println("----------");

        Table<String, String, String> table = HashBasedTable.create();
        table.put("2022", "01", "01");
        table.put("2022", "03", "03");
        table.put("2022", "05", "05");
        table.put("2023", "02", "02");
        table.put("2023", "04", "04");
        table.put("2023", "05", "05");
        System.out.println(table.row("2022"));
        System.out.println("----------");

        Joiner joiner = Joiner.on(",").skipNulls();
        String join = joiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println(join);
        Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
        for (String s : splitter.split(" a,     ,b,,")) {
            System.out.println(s);
        }
        System.out.println("----------");

        CharMatcher digit = CharMatcher.digit();
        System.out.println(digit.retainFrom("3d,.&^*6"));
        System.out.println(digit.removeFrom("3d,.&^*6"));

        //缓存中不存在的通过load方法填充
        LoadingCache<String, String> lc = CacheBuilder
        .newBuilder()
        .expireAfterWrite(1, TimeUnit.SECONDS)
        .build(new CacheLoader<String, String>() {
            @Override
            public String load(String key){
                return "";
            }}
        );
        try {
            System.out.println(lc.get(""));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
