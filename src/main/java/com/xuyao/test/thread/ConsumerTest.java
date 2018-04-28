package com.xuyao.test.thread;

import java.util.LinkedList;


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
