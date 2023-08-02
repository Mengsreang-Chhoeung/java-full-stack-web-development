package org.ms.inheritance;

// Sub Class
public class Dog extends Animal {
    // override attributes
    public String name = "Dudu";

    // override methods
    public void output() {
        super.output();
        System.out.println("Hello Dog!");
    }
}
