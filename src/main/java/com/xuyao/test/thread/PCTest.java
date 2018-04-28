package com.xuyao.test.thread;

public class PCTest {
    public static void main(String[] args){
        ProducerTest pt = new ProducerTest();
        ConsumerTest ct = new ConsumerTest();
        pt.start();
        ct.start();
    }
}
