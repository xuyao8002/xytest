package com.xuyao.test.pattern.AbstractFactory;

/**
 * Created by xuyao on 2018/10/5.
 */
public class FishFactory implements Factory {
    @Override
    public AnimalFactory createAnimalFacotry() {
        return new Fish();
    }
}
