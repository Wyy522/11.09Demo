package com.easy.archiecture.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
//@Named
//@Controller
//@Repository
public class PersonServiceImpl {


    public void printName() {
        System.out.println("abc");
    }
}
