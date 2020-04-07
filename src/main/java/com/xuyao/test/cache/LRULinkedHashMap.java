package com.xuyao.test.cache;

import java.util.LinkedHashMap;
import java.util.Map;

class LRULinkedHashMap extends LinkedHashMap {

    private int capacity;

    public LRULinkedHashMap(int capacity) {
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.values().size() > capacity;
    }
}