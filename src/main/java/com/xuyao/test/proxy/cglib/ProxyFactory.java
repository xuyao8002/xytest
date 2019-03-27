package com.xuyao.test.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory {

    public static <T> T getProxyInstance(Class<T> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before " + method.getName());
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println("after " + method.getName());
                return invoke;
            }
        });
        return (T) enhancer.create();
    }
}
