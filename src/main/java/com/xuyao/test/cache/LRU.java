package com.xuyao.test.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRU<K, V> {

    private int capacity;
    private Map<K, V> map = new HashMap<>();
    private LinkedList<K> list = new LinkedList<>();

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public void put(K key, V value){
        if (map.containsKey(key)) {
            list.remove(key);
        }else{
            if (list.size() == capacity) {
                K k = list.removeLast();
                map.remove(k);
            }
        }
        list.addFirst(key);
        map.put(key, value);
    }

    public V get(K key){
        V v;
        if (!map.containsKey(key)) {
            return null;
        }else{
            v = map.get(key);
            put(key, v);
        }
        return v;
    }

}