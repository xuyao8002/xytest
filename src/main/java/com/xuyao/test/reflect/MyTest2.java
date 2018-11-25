package com.xuyao.test.reflect;

import com.xuyao.test.Person;

import java.lang.reflect.Field;

public class MyTest2 {
    public static void main(String[] args)  {
        Person person = new Person();
        person.setAge(18);
        person.setName("xuyao");
        
        try {
            Class<?> string = Class.forName("com.xuyao.test.Person");
            Object o = string.newInstance();

            Field[] declaredFields = string.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                declaredField.set(o, declaredField.get(person));
            }
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static int inc(){
        int x;
        try{
            x = 1;
            return x;
        } catch (Exception e){
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
        }
    }


}

