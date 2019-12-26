package com.xuyao.test.other.objectinfo;

public class Info {
   private Info instance;

   public String name;

   private static String gender;

   public static int age;
 
   public void setSample(Object instance) { 
       this.instance = (Info) instance;
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
        Info.gender = gender;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        Info.age = age;
    }
}