package com.xuyao.test.pattern.strategy;

/**
 * Created by xuyao on 2018/10/6.
 */
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = 3;
        Context context = new Context(new Add());
        System.out.println(context.calc(a, b));
        context = new Context(new Substract());
        System.out.println(context.calc(a, b));
        context = new Context(new Multiply());
        System.out.println(context.calc(a, b));
        context = new Context(new Divide());
        System.out.println(context.calc(a, b));
    }
}
