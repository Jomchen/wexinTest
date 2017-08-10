package com.weixin.entity;

import com.weixin.common.AbstractParent;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZPC on 2017/3/17.
 */
public class Customer implements AbstractParent {

    private Integer tid;
    private String cname;               // 学生姓名
    private Integer age;                // 学生年龄
    private String address;             // 学生地址
    private Long classId;               // 学生对应班级外键
    private Date birthday;              // 学生生日

    public Customer() {
    }

    public Customer(Integer tid, String cname, Integer age, String address, Long classId, Date birthday) {
        this.tid = tid;
        this.cname = cname;
        this.age = age;
        this.address = address;
        this.classId = classId;
        this.birthday = birthday;
    }

    public Integer getTid() {
        return tid;
    }

    public String getCname() {
        return cname;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public Long getClassId() {
        return classId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (tid != null ? !tid.equals(customer.tid) : customer.tid != null) return false;
        if (cname != null ? !cname.equals(customer.cname) : customer.cname != null) return false;
        if (age != null ? !age.equals(customer.age) : customer.age != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (classId != null ? !classId.equals(customer.classId) : customer.classId != null) return false;
        return birthday != null ? birthday.equals(customer.birthday) : customer.birthday == null;
    }

    @Override
    public int hashCode() {
        int result = tid != null ? tid.hashCode() : 0;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "tid=" + tid +
                ", cname='" + cname + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", classId=" + classId +
                ", birthday=" + birthday +
                '}';
    }
}
