package com.xuyao.test.algorithm;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightedRoundRobin<E> {

    private List<WeightedEntry<E>> entries;
    private int totalWeight;
    private Random random;

    private AtomicInteger atomicInteger;

    public WeightedRoundRobin(List<WeightedEntry<E>> entries, int totalWeight, Random random) {
        this.entries = entries;
        this.totalWeight = totalWeight;
        this.random = random;
        atomicInteger = new AtomicInteger(0);
    }

    public WeightedRoundRobin() {
        entries = new ArrayList<>();
        totalWeight = 0;
        random = new Random();
        atomicInteger = new AtomicInteger(0);
    }

    public void addEntry(E item, int weight) {
        entries.add(new WeightedEntry<>(item, weight));
        totalWeight += weight;
    }

    public E getEntry() {
        if(entries.isEmpty()){
            return null;
        }
        int r = random.nextInt(totalWeight); //随机
//        r = atomicInteger.getAndIncrement() % totalWeight; //准确
        for(WeightedEntry<E> entry : entries){
            r -= entry.weight;
            if(r < 0){
                return entry.item;
            }
        }
        return entries.get(entries.size() - 1).item;
    }

    private static class WeightedEntry<E> {
        private E item;
        private int weight;

        public WeightedEntry(E item, int weight) {
            this.item = item;
            this.weight = weight;
        }
    }

}
