package com.easy.archiecture.entity;

import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = 2021745504395240314L;

    private int id;
    private int age;
    private String name;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
