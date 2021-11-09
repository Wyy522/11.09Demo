package com.easy.archiecture.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Company {

    @Autowired
//    @Inject
//    @Resource
    private PersonServiceImpl person;

    //applicationContext.getBean("xxxxx");


    public void getPersonName() {
        person.printName();
    }

}
