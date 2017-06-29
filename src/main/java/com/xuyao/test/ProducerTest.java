package com.xuyao.test;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by xuyao on 2017/6/27.
 */
public class ProducerTest extends Thread{
    public static LinkedList<Integer> list = new LinkedList<Integer>();
    static int size = 5;
    public static void main(String[] args){

    }

    @Override
    public void run() {
        Random r = new Random();
        while(true){
            synchronized (list){
                while(list.size() == size){
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int i = r.nextInt();
                list.add(i);
                System.out.println("produce " + i);
                list.notifyAll();
            }
        }
    }
}
