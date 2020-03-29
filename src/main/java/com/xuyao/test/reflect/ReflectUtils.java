package com.xuyao.test.reflect;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.xuyao.test.Person;
import com.xuyao.test.annotation.FieldAnno;
import com.xuyao.test.annotation.TestAnno;
import com.xuyao.test.annotation.TypeAnno;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Map;

public class ReflectUtils {

    /**
     * 获取对象属性，耗时排序，getByReflectAsmIndex < getByReflect < getByReflectAsm
     */
    public static <T> T getByReflectAsmIndex(Object object, String getMethod){
        MethodAccess methodAccess = MethodAccess.get(object.getClass());
        int index = methodAccess.getIndex(getMethod);
        return (T) methodAccess.invoke(object, index);
    }

    public static <T> T getByReflect(Object object, String getMethod) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method declaredMethod = object.getClass().getDeclaredMethod(getMethod);
        return (T) declaredMethod.invoke(object);
    }

    public static <T> T getByReflectAsm(Object object, String getMethod){
        MethodAccess methodAccess = MethodAccess.get(object.getClass());
        return (T) methodAccess.invoke(object, getMethod);
    }


    public static <T> T getByProperty(Object object, String field) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(field)) {
                Method readMethod = propertyDescriptor.getReadMethod();
                return (T) readMethod.invoke(object);
            }
        }
        return null;
    }

    private static void changeAnnoValTest() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
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
        Field memberValues = h.getClass().getDeclaredField("memberValues");
        memberValues.setAccessible(true);
        Map memberValuesMap = (Map) memberValues.get(h);
        memberValuesMap.put(key, newVal);
    }


}
