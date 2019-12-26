package com.xuyao.test.reflect;

import com.xuyao.test.SampleSub;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
	
	public static void main(String[] args) {
		readFields();
    }



    public static void readFields(){
        Class<SampleSub> sub = SampleSub.class;
        //获取当前类中所有字段，访问权限：public、protected、default、private
        Field[] declaredFields = sub.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        System.out.println();
        //获取当前类及父类中所有公共字段，访问权限：public
        Field[] fields = sub.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }

}
