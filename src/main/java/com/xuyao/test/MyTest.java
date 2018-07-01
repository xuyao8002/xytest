package com.xuyao.test;

import com.xuyao.test.proxy.ProxyFactory;

public class MyTest {

    public static void main(String[] args) {
        ProxyFactory pf = new ProxyFactory(new SayServiceImpl());
        ISayService say = (ISayService) pf.getProxyInstance();
        say.say("hi, xuyao");
    }

}
