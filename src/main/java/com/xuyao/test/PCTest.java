package com.xuyao.test;

/**
 * Created by xuyao on 2017/6/27.
 */
public class PCTest {
    public static void main(String[] args){
        ProducerTest pt = new ProducerTest();
        ConsumerTest ct = new ConsumerTest();
        pt.start();
        ct.start();
    }
}
