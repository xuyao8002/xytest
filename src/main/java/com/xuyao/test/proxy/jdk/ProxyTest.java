package com.xuyao.test.proxy.jdk;

public class ProxyTest {

    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ISayService say = ProxyFactory.getProxyInstance(new SayServiceImpl());
        say.say("hi, xuyao");
    }

}
