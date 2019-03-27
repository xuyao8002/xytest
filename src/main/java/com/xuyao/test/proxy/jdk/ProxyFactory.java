package com.xuyao.test.proxy.jdk;


import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

//    private Object target;
//    public ProxyFactory(Object target){
//        this.target=target;
//    }

    public static <T> T getProxyInstance(T target){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("执行前");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("执行后");
                        return returnValue;
                    }
                }
        );
    }

}
