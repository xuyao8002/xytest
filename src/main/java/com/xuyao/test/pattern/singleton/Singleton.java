package com.xuyao.test.pattern.singleton;

public class Singleton {

//    ---------------------------1--------------------------------
//    private static Singleton singleton = new Singleton();
//
//    private Singleton(){};
//
//    public static Singleton getSingleton(){
//        return singleton;
//    }

//    ---------------------------2--------------------------------
//    private static volatile Singleton singleton;
//    private Singleton(){};
//    public static  Singleton getSingleton(){
//        if(singleton == null){
//            synchronized (Singleton.class){
//                if(singleton == null)
//                    singleton = new Singleton();
//            }
//        }
//        return singleton;
//    }

//    ---------------------------3--------------------------------
//    private Singleton(){};
//    private static class SingletonHolder{
//        private final static Singleton SINGLETON = new Singleton();
//    }
//    public static  Singleton getSingleton(){
//        return SingletonHolder.SINGLETON;
//    }



    public static void main(String[] args) throws Exception {

    }

}
