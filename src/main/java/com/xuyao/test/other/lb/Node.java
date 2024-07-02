package com.xuyao.test.other.lb;

public class Node {
    private final String name;
    private final int weight;

    public Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

}
