package com.easy.archiecture.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTestMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotationConfig.xml");
        Company company = (Company) context.getBean("company");
        company.getPersonName();
    }
}
