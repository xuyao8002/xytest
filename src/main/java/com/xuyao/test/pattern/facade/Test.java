package com.xuyao.test.pattern.facade;

/**
 * Created by xuyao on 2018/10/6.
 */
public class Test {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        computer.shutdown();

    }
}
