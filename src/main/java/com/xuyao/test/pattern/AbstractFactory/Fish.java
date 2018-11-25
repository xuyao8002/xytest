package com.xuyao.test.pattern.AbstractFactory;

/**
 * Created by xuyao on 2018/10/5.
 */
public class Fish implements AnimalFactory {
    @Override
    public void buyAnimal() {
        System.out.println("buy a fish");
    }
}
