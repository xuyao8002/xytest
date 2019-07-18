package com.xuyao.test.algorithm.shuffle;

import java.util.Random;

public class Knuth {

    public static <T> void shuffle(T[] arr) {
        int length = arr.length;
        Random random = new Random();
        for(int i = length - 1; i > 0; i--){
            swap(arr, i, random.nextInt(i));
        }
    }

    private static <T> void swap(T[] arr, int x, int y){
        T tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
