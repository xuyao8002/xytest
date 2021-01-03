package com.xuyao.test.pattern.Decorator;

public class Draw implements Action{
    @Override
    public void doSomething() {
        System.out.print("画画");
    }
}
