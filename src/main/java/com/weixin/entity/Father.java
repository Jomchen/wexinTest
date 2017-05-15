package com.weixin.entity;

import com.weixin.common.AbstractParent;


/**
 * Created by zpc on 2017/5/4.
 */
public class Father implements AbstractParent {

    private String name = "父亲";

    private int age = 20;

    public Father() {
    }

    public Father(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Father father = (Father) o;

        if (age != father.age) return false;
        return name != null ? name.equals(father.name) : father.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "【Father{" +
                "name='" + name + '\'' +
                ", age=" + age +
                "}】";
    }
}
