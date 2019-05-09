package com.xuyao.test.reflect;

import com.xuyao.test.Person;
import com.xuyao.test.annotation.FieldAnno;
import com.xuyao.test.annotation.TestAnno;
import com.xuyao.test.annotation.TypeAnno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ReflectUtils {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {

        Class<Person> personClass = Person.class;
        TypeAnno annotation = personClass.getAnnotation(TypeAnno.class);
        System.out.println("before: " + annotation.name());
        changeAnnoVal(Person.class, TypeAnno.class, "name", "newPerson");
        System.out.println("after: " + annotation.name());

        Method getAihao = personClass.getDeclaredMethod("getAihao");
        TestAnno annotation1 = getAihao.getAnnotation(TestAnno.class);
        System.out.println("before: " + annotation1.author());
        changeAnnoVal(getAihao, TestAnno.class, "author", "newPlay");
        System.out.println("after: " + annotation1.author());

        Field age = personClass.getDeclaredField("age");
        FieldAnno annotation2 = age.getAnnotation(FieldAnno.class);
        System.out.println("before: " + annotation2.chn());
        changeAnnoVal(age, FieldAnno.class, "chn", "66");
        System.out.println("after: " + annotation2.chn());
    }

    public static void changeAnnoVal(Class clazz, Class annoClazz, String key, String newVal) throws NoSuchFieldException, IllegalAccessException {
        Annotation annotation = clazz.getAnnotation(annoClazz);
        changeAnnoVal(key, newVal, annotation);
    }

    public static void changeAnnoVal(Method method, Class annoClazz, String key, String newVal) throws NoSuchFieldException, IllegalAccessException {
        Annotation annotation = method.getAnnotation(annoClazz);
        changeAnnoVal(key, newVal, annotation);
    }

    public static void changeAnnoVal(Field field, Class annoClazz, String key, String newVal) throws NoSuchFieldException, IllegalAccessException {
        Annotation annotation = field.getAnnotation(annoClazz);
        changeAnnoVal(key, newVal, annotation);
    }

    private static void changeAnnoVal(String key, String newVal, Annotation annotation) throws NoSuchFieldException, IllegalAccessException {
        InvocationHandler h = Proxy.getInvocationHandler(annotation);
        Field hField = h.getClass().getDeclaredField("memberValues");
        hField.setAccessible(true);
        Map memberValues = (Map) hField.get(h);
        memberValues.put(key, newVal);
    }


}
