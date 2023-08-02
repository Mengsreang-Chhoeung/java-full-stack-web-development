package org.ms;

public class Main6 {
    public static void main(String[] args) {
        Student kok = new Student();
        Student koko = new Student();
        koko.setName("Koko");
        System.out.println("Student Number 1 name: " + kok.name);
        kok.greeting();
        System.out.println("Student Number 2 name: " + koko.name);
        koko.greeting();
    }
}
