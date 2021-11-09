package com.easy.archiecture.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigTestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Company company = (Company) context.getBean("company");
        company.getPersonName();

        SelfEvenetPub eventPub = (SelfEvenetPub)context.getBean("selfEvenetPub");
        eventPub.publish();
        eventPub.publish();

    }
}
