package com.xuyao.test.other.ConsistentHash;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {

    private int hashNums;

    private int virtualNodeNums;

    private SortedMap<Integer, String> sortedMap;

    public ConsistentHash(int hashNums, int virtualNodeNums) {
        this.hashNums = hashNums;
        this.virtualNodeNums = virtualNodeNums;
        this.sortedMap = new TreeMap<>();
    }

    public String getNode(String key){
        return null;
    }

}
