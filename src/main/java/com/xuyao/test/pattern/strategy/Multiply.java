package com.xuyao.test.pattern.strategy;

/**
 * Created by xuyao on 2018/10/6.
 */
public class Multiply implements Calculate {
    @Override
    public int calc(int a, int b) {
        return a * b;
    }
}
