package com.xuyao.test.sort;


public class ReverseSort {
    public static void main(String[] args) {
        int[]  arr = {9, 5, 2 , 7, 6};
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i]  =  arr[arr.length - 1 - i];
            arr[arr.length - i - 1] = temp;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
