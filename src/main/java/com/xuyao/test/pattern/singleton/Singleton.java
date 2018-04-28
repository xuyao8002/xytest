package com.xuyao.test.pattern.singleton;

public class Singleton {

    private static Singleton singleton = new Singleton();

    private Singleton(){};

    public static Singleton getSingleton(){
        return singleton;
    }
    
    public static String ss(){
    	System.out.println("hello, xuyao");
    	return "world";
    }
    
    private static int p(int i, int j){
    	System.out.println(i+j);
    	return i+j;
    }
}
