package com.xuyao.test.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyTest1 {
	
	public static void main(String[] args) throws Exception{
		Class cla = Class.forName("com.xuyao.test.pattern.singleton.Singleton");//Singleton.class;
		System.out.println(cla.getName());
		System.out.println(cla.getSimpleName());
		System.out.println(cla.getModifiers());
		System.out.println(cla.getPackage());
		System.out.println(cla.getSuperclass());
		Class[] interfaces = cla.getInterfaces();
		if(interfaces.length > 0){
			for(int i = 0; i < interfaces.length; i++){
				System.out.print(interfaces[i] + ", ");
			}
		}
		Constructor[] constructors = cla.getConstructors();
		if(constructors.length > 0){
			for(int i = 0; i < constructors.length; i++){
				System.out.print(constructors[i] + ", ");
			}
		}
		Method[] methods = cla.getMethods();
		if(methods.length > 0){
			for(int i = 0; i < methods.length; i++){
				System.out.print(methods[i] + ", ");
			}
		}
		Field[] fields = cla.getFields();
		if(fields.length > 0){
			for(int i = 0; i < fields.length; i++){
				System.out.print(fields[i] + ", ");
			}
		}
		Annotation[] annotations = cla.getAnnotations();
		if(annotations.length > 0){
			for(int i = 0; i < annotations.length; i++){
				System.out.print(annotations[i] + ", ");
			}
		}
	}
}
