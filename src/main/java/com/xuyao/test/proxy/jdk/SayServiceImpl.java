package com.xuyao.test.proxy.jdk;


public class SayServiceImpl implements ISayService{

    @Override
    public void say(String string) {
        System.out.println("I say: " + string);
    }
}
