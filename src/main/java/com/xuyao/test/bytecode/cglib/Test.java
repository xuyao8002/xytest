package com.xuyao.test.bytecode.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Test {
    public static void main(String[] args) {
        Base base = new Base();
        Proxy proxy = new Proxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(base.getClass());
        enhancer.setCallback(proxy);
        Base proxyObj = (Base) enhancer.create();
        proxyObj.say();
    }
}
