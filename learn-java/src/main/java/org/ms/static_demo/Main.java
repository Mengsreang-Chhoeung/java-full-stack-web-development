package org.ms.static_demo;

public class Main {
    public static void main(String[] args) {
        Student kok = new Student();
        Student.school = "IT";
        kok.setId(1001);
        kok.setName("Kok");
        System.out.println("Student 1 ID: " + kok.getId() + " Name: " + kok.getName() + " and School: " + Student.school);
        Student.outputSchool();

        Student koko = new Student();
        Student.school = "RUPP";
        koko.setId(1002);
        koko.setName("Koko");
        System.out.println("Student 2 ID: " + koko.getId() + " Name: " + koko.getName() + " and School: " + Student.school);
        Student.outputSchool();

    }
}
