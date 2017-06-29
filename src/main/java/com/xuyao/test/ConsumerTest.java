package com.xuyao.test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuyao on 2017/6/27.
 */
public class ConsumerTest extends Thread{
    public static void main(String[] args){

    }

    @Override
    public void run() {
        LinkedList<Integer> list = ProducerTest.list;
        while(true){
            synchronized (list){
                while(list.isEmpty()){
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("consumer list" + list.poll());
                list.notifyAll();
            }
        }
    }
}
