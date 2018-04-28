package com.xuyao.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyTest {
	
	public static void main(String[] args) throws Exception{
		Class cla = Class.forName("com.xuyao.test.pattern.singleton.Singleton");//Singleton.class;
		Constructor declaredConstructor = cla.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);
		Method declaredMethod = cla.getDeclaredMethod("ss", null);
		Object newInstance = declaredConstructor.newInstance();
		
		declaredMethod.invoke(newInstance, null);
		
		declaredMethod = cla.getDeclaredMethod("p", int.class, int.class);
		declaredMethod.setAccessible(true);
		declaredMethod.invoke(newInstance, 9, 8);
		//System.out.println();
	}
}
