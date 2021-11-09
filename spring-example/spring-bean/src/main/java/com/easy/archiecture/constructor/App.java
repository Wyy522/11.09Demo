package com.easy.archiecture.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        Person person = (Person) applicationContext.getBean("person");
        person.input();
        Person person1 = (Person) applicationContext.getBean("person");

        System.out.println(person == person1);


//        Person p1=new Person();
//        p1.setName("aaaaa");
//        p1.input();
//        Class<?> clazz = Class.forName("com.easy.archiecture.constructor.Person");
//        Object o = clazz.newInstance();
    }
}
