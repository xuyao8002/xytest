package com.xuyao.test.proxy.cglib;

public class Test {
    public static void main(String[] args) {
        Base proxyInstance = ProxyFactory.getProxyInstance(Base.class);
        proxyInstance.say();
    }
}
