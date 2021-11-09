package com.easy.archiecture.javaconfig;

import org.springframework.stereotype.Service;

@Service
//这里spring中会把类的第一个字母变成小写，然后放到IOC容器中。 personServiceImpl
public class PersonServiceImpl {


    public void printName() {
        System.out.println("abc");
    }
}
