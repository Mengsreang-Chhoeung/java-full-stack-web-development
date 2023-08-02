package org.ms.inheritance;

public class Main2 {
    public static void main(String[] args) {
        Teacher kok = new Teacher();
        kok.setId(1);
        kok.setName("Kok");
        kok.setAge(30);
        kok.setSex("Male");
        System.out.println("Hello Teacher 1 ID: " + kok.getId() + " Name: " + kok.getName() + " Sex: " + kok.getSex() + " and Age: " + kok.getAge());
        kok.getIdentifier();
    }
}
