package com.xuyao.test.algorithm.heap;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class MaxMinHeap {

    private static int[] topN(int[] arr, int n, Comparator comparator) {
        int length = arr.length;
        if(n <= 0 || length < n){
            return null;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(n, comparator);
        for (int i = 0; i < length; i++) {
            int val = arr[i];
            if(i < n){
                heap.offer(val);
            }else{
                if(Objects.compare(heap.peek(), val, comparator) < 0){
                    heap.poll();
                    heap.offer(val);
                }
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = heap.poll();
        }
        return result;
    }

    /**
     * 大顶堆
     * @param arr
     * @param n
     * @return
     */
    public static int[] topNMin(int[] arr, int n){
        return topN(arr, n, Comparator.reverseOrder());
    }

    /**
     * 小顶堆
     * @param arr
     * @param n
     * @return
     */
    public static int[] topNMax(int[] arr, int n){
        return topN(arr, n, Comparator.naturalOrder());
    }

}
