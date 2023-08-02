package org.ms.inner_demo;

public class Main {
    public static void main(String[] args) {
        Student kok = new Student();
        kok.id = 1001;
        kok.name = "Kok";
        Student.StudentDetails kokDetails = kok.new StudentDetails();
        kokDetails.school = "IT";
        System.out.println("Student 1 ID: " + kok.id + " name: " + kok.name + " and details: " + kokDetails.school + " school.");

        Student.StudentSubject koko = new Student.StudentSubject();
        koko.name = "Math";
        System.out.println("Total Subject: " + koko.getTotal());
    }
}
