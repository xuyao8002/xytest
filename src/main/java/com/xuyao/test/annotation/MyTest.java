package com.xuyao.test.annotation;

import com.xuyao.test.Person;

import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) {

    	Class cla = Person.class;
        for(Method method : cla.getMethods()){
            TestAnno anno = method.getAnnotation(TestAnno.class);
            if(anno != null){
                System.out.println(method.getName());
                System.out.println(anno.author());
                System.out.println(anno.priority());
                System.out.println(anno.status());
            }
        }

    }

}
