package com.xuyao.test.classloader;


import com.xuyao.test.classloader.spi.ISay;

import java.util.ServiceLoader;

public class Test {

    public static void main(String[] args) {
//        String classDataRootPath = "E:\\workspace\\xytest\\target\\classes";
//        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
//        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
//        String className = "com.xuyao.test.Sample";
//        try {
//            Class<?> class1 = fscl1.findClass(className);
//            Object obj1 = class1.newInstance();
//            Class<?> class2 = fscl2.findClass(className);
//            Object obj2 = class2.newInstance();
//            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
//            setSampleMethod.invoke(obj1, obj2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ServiceLoader<ISay> load = ServiceLoader.load(ISay.class);
        for (ISay iSay : load) {
            iSay.say();
        }
    }

}
