package com.xuyao.test.pattern.Decorator;

/**
 * Created by xuyao on 2018/10/6.
 */
public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Appearance appearance) {
        super(appearance);
    }

    @Override
    public void seems() {
        super.seems();
        System.out.println("dress up");
        System.out.println("nice");
    }
}
