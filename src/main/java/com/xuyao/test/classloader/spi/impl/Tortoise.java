package com.xuyao.test.classloader.spi.impl;

import com.xuyao.test.classloader.spi.ISay;

public class Tortoise implements ISay {
    @Override
    public void say() {
        System.out.println("噢");
    }
}
