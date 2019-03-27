package com.xuyao.test.proxy.jdk;

public class ProxyTest {

    public static void main(String[] args) {
        ISayService say = ProxyFactory.getProxyInstance(new SayServiceImpl());
        say.say("hi, xuyao");
    }

}
