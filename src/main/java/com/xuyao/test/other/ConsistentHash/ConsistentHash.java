package com.xuyao.test.other.ConsistentHash;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {

    /**
     * 虚拟节点数量
     */
    private int virtualNodeNums;

    /**
     * 节点hash-节点
     */
    private static TreeMap<Integer, String> hashNodeMap;

    /**
     * 节点信息
     */
    private String[] nodes;

    /**
     * 虚拟节点分隔符
     */
    private static final String VIRTUAL_NODE_SEPARATOR = "##";

    /**
     * 虚拟节点别名
     */
    private static final String VIRTUAL_NODE_ALIAS = "VN";

    /**
     * 虚拟节点默认数量
     */
    private static final int DEFAULT_VIRTUAL_NODE_NUMS = 10;

    private ConsistentHash(String[] nodes, int virtualNodeNums) {
        this.nodes = nodes;
        this.virtualNodeNums = virtualNodeNums;
    }

    public static ConsistentHash getInstance(String[] nodes, int virtualNodeNums){
        if(nodes == null || nodes.length == 0){
            throw new IllegalArgumentException("nodes不能为空");
        }
        if(virtualNodeNums <= 0){
            throw new IllegalArgumentException("virtualNodeNums必须大于0");
        }
        return getConsistentHash(nodes, virtualNodeNums);
    }

    public static ConsistentHash getInstance(String[] nodes){
        if(nodes == null || nodes.length == 0){
            throw new IllegalArgumentException("nodes不能为空");
        }
        int virtualNodeNums = DEFAULT_VIRTUAL_NODE_NUMS;
        return getConsistentHash(nodes, virtualNodeNums);
    }

    private static ConsistentHash getConsistentHash(String[] nodes, int virtualNodeNums) {
        ConsistentHash consistentHash = new ConsistentHash(nodes, virtualNodeNums);
        hashNodeMap = new TreeMap<>();
        int length = nodes.length;
        for (int i = 0; i < length; i++) {
            hashNodeMap.put(hash(nodes[i]), nodes[i]);
            for (int v = 0; v < virtualNodeNums; v++) {
                hashNodeMap.put(hash(nodes[i] + VIRTUAL_NODE_SEPARATOR + VIRTUAL_NODE_ALIAS + v), nodes[i]);
            }
        }
        return consistentHash;
    }

    public String getNode(String key){
        int from = hash(key);
        SortedMap<Integer, String> tailMap = hashNodeMap.tailMap(from);
        if(tailMap.isEmpty()){
            tailMap = hashNodeMap.tailMap(0);
        }
        return hashNodeMap.get(tailMap.firstKey());
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
        return hash & 0x7FFFFFFF;
    }

    public int getVirtualNodeNums() {
        return virtualNodeNums;
    }

    public String[] getNodes() {
        return nodes;
    }
}
