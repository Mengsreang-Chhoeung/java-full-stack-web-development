package org.ms;

public class Student {
    // attributes or fields
    String name = "Kok";

    // methods
    void setName(String _name) {
        name = _name.toUpperCase();
    }

    void greeting() {
        System.out.println("Welcome " + name);
    }
}
