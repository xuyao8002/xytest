package com.xuyao.test.proxy;

import com.xuyao.test.ISayService;
import com.xuyao.test.SayServiceImpl;

public class ProxyTest {

    public static void main(String[] args) {
        ProxyFactory pf = new ProxyFactory(new SayServiceImpl());
        ISayService say = (ISayService) pf.getProxyInstance();
        say.say("hi, xuyao");
    }

}
