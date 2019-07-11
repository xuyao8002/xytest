package com.xuyao.test.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LRU {

    private Map<Integer, Node> map = new HashMap<>();
    LinkedList<Node> linkedList = new LinkedList();
    private int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            int val = map.get(key).getVal();
            put(key, val);
            return val;
        }
    }
    
    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            linkedList.remove(map.get(key));
            linkedList.addFirst(node);
            map.put(key, node);
        }else{
            if(linkedList.size() == capacity){
                Node node1 = linkedList.removeLast();
                map.remove(node1.getKey());
            }
            linkedList.addFirst(node);
            map.put(key, node);
        }
    }

    class Node {
        private int key;
        private int val;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
        public int getKey() {
            return key;
        }
        public int getVal() {
            return val;
        }
    }

}