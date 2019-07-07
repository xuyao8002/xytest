package com.xuyao.test;

public class Test {

    public static void main(String[] args) {
        ClassLoader classLoader = Test.class.getClassLoader();
        while(classLoader != null){
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }

}
