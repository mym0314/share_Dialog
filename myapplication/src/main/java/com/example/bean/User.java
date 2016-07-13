package com.example.bean;

/**
 * Created by mym_0314 on 2016/6/9.
 */

import java.util.Date;

//"(_id integer primary key autoincrement," +
//        "name varchar(20) ," +
//        "sex check in(sex in('男','女')，" +
//        "age integer)," +
//        "date data)";
public class User {
    private int _id;
    private String name ;
    private String sex;
    private int age;
    private Date date;

    public User() {
    }

    public User(int _id, String name, String sex, int age, Date date) {
        this._id = _id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
