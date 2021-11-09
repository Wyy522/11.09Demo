package com.easy.archiecture.staticfactory;

public class PersonFactory {
    public Person getPerson(String arg) {
        if (arg.equalsIgnoreCase("chinese")) {
            return new Chinese();
        } else {
            return new American();
        }
    }
}
