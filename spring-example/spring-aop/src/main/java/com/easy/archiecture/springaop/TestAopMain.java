package com.easy.archiecture.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAopMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("SpringAopTest.xml");
        Student student = (Student) context.getBean("student");
        student.getName();
        student.getAge();
        student.printThrowException();
    }
}
