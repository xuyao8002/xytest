package com.xuyao.test.other.lb;

import java.util.List;
import java.util.Random;

public class WeightedRoundRobin implements Lb{

    private final List<Node> nodeList;
    private final int totalWeight;
    private final Random random = new Random();

    public WeightedRoundRobin(List<Node> nodeList) {
        this.nodeList = nodeList;
        totalWeight = nodeList.stream().mapToInt(Node::getWeight).sum();
    }

    @Override
    public Node next() {
        int r = random.nextInt(totalWeight);
        int index = 0;
        for(int i = 0; i < nodeList.size(); i++){
            if(r < 0){
                return nodeList.get(index);
            }
            r -= nodeList.get(i).getWeight();
            index = i;
        }
        return nodeList.get(index);
    }
}
