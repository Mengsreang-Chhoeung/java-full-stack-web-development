package org.ms.inheritance;

public class Main3 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.output();
        System.out.println("Dog name: " + dog.name);
        Cat cat = new Cat();
        cat.output();
        System.out.println("Cat name: " + cat.name);
    }
}
