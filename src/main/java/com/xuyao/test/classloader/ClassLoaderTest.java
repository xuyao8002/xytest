package com.xuyao.test.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader cl = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if(is == null) {
                    return super.loadClass(name);
                }
                byte[] b;
                try {
                    b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };
        Object obj = cl.loadClass("com.xuyao.test.Person");
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.xuyao.test.Person);
    }

}

