package com.xuyao.test;

public class Sample {
   private Sample instance;

   public String name;

   private static String gender;

   public static int age;
 
   public void setSample(Object instance) { 
       this.instance = (Sample) instance; 
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        Sample.gender = gender;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        Sample.age = age;
    }
}