package com.xuyao.test.other.ConsistentHash;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {

    private int virtualNodeNums;

    private static TreeMap<Integer, String> treeMap;

    private String[] ips;

    private ConsistentHash(String[] ips, int virtualNodeNums) {
        this.ips = ips;
        this.virtualNodeNums = virtualNodeNums;
    }

    public static ConsistentHash getInstance(String[] ips, int virtualNodeNums){
        if(ips == null || ips.length == 0){
            throw new IllegalArgumentException("ips不能为空");
        }
        if(virtualNodeNums < 0){
            throw new IllegalArgumentException("virtualNodeNums必须大于0");
        }
        ConsistentHash consistentHash = new ConsistentHash(ips, virtualNodeNums);
        treeMap = new TreeMap<>();
        int length = ips.length;
        for (int i = 0; i < length; i++) {
            treeMap.put(hash(ips[i]), ips[i]);
        }
        return consistentHash;
    }

    public String getNode(String key){
        int i = hash(key);
        SortedMap<Integer, String> tailMap = treeMap.tailMap(i);
        if(tailMap.isEmpty()){
            tailMap = treeMap.tailMap(0);
        }
        return treeMap.get(tailMap.firstKey());
    }

    private static int hash(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        if(hash < 0){
            hash = -hash;
        }
        return hash;
    }

}
