package com.xuyao.test;

import com.xuyao.test.annotation.TestAnno;

public class Person {

    private String name;

    private Integer age;

    private String hobby;

    //@JSONField(name = "nima")
    private String aihao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @TestAnno(author = "xuye")
    public String getAihao() {
        return hobby;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", aihao='" + aihao + '\'' +
                '}';
    }
}
