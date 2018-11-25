package com.xuyao.test.pattern.AbstractFactory;

/**
 * Created by xuyao on 2018/10/5.
 */
public class Test {
    public static void main(String[] args) {
        Factory factory = new TortoiseFactory();
        AnimalFactory animalFacotry = factory.createAnimalFacotry();
        animalFacotry.buyAnimal();
    }
}
