package com.weixin.entity;

import com.weixin.common.AbstractParent;


/**
 * Created by zpc on 2017/5/4.
 */
public class Son extends Father implements AbstractParent {

    private String name = "儿子";
    private int age = 10;

    public Son() {
    }

    public Son(String name, int age, String name1) {
        super(name, age);
        this.name = name1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Son son = (Son) o;

        if (age != son.age) return false;
        return name != null ? name.equals(son.name) : son.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "【Son{" +
                "name='" + name + '\'' +
                ", age=" + age +
                "}】";
    }

}
