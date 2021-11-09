package com.easy.archiecture.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Company {

    private PersonServiceImpl person;

//    public Company(PersonServiceImpl person) {
//        this.person = person;
//    }
    @Autowired
    public void setPerson(PersonServiceImpl person){
        this.person=person;
    }


    public void getPersonName() {
        person.printName();
    }

}
