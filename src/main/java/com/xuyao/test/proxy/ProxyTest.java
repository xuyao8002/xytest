package com.xuyao.test.proxy;

public class ProxyTest {

    public static void main(String[] args) {
        ProxyFactory pf = new ProxyFactory(new SayServiceImpl());
        ISayService say = (ISayService) pf.getProxyInstance();
        say.say("hi, xuyao");
    }

}
