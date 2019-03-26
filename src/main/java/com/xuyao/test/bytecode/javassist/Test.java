package com.xuyao.test.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class Test {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.xuyao.test.Hello");
        CtMethod ctMethod = CtNewMethod.make("public void say(){}", ctClass);
        ctMethod.insertBefore("System.out.println(\"Hello ^_^\");");
        ctClass.addMethod(ctMethod);

        //执行方法
        Class<?> clazz = ctClass.toClass();
        Object obj = clazz.newInstance();
        obj.getClass().getMethod("say", new Class[]{}).invoke(obj, new Object[]{});

        //保存文件
//        ctClass.writeFile("G:\\");


    }

}
