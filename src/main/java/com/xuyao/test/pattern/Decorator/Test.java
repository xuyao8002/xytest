package com.xuyao.test.pattern.Decorator;

/**
 * Created by xuyao on 2018/10/6.
 */
public class Test {
    public static void main(String[] args) {
        Appearance appearance = new ConcreteDecorator(new ConcretAppearance());
        appearance.seems();
    }
}
