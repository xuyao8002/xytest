package com.xuyao.test.pattern.strategy;

/**
 * Created by xuyao on 2018/10/6.
 */
public class Context {
    private Calculate calculate;

    public Context(Calculate calculate) {
        this.calculate = calculate;
    }

    public int calc(int a, int b){
        return calculate.calc(a, b);
    }

}
