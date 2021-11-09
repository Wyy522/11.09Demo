package com.easy.archiecture.constructor;

public class Person {
    private String name;

    public Person() {
        System.out.println("------->init by no args constructor<--------");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void input() {
        System.out.println("just print------>" + name);
    }
}
