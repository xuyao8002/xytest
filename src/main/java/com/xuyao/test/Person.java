package com.xuyao.test;

import com.xuyao.test.annotation.FieldAnno;
import com.xuyao.test.annotation.TestAnno;
import com.xuyao.test.annotation.TypeAnno;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@TypeAnno(name = "person")
public class Person implements Serializable {

//    private static final long serialVersionUID = -8815168358655024061L;
    private String name;

    @FieldAnno(chn = "99")
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
        if (StringUtils.isBlank(hobby)) {
            hobby = "playing";
        }
        this.hobby = hobby;
    }

    @TestAnno(author = "play")
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
