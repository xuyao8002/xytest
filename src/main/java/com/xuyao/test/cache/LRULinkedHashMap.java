package com.xuyao.test.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V> {

    private int capacity;

    public LRULinkedHashMap(int capacity) {
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
}