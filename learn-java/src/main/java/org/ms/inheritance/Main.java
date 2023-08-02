package org.ms.inheritance;

public class Main {
    public static void main(String[] args) {
        Student stu1 = new Student();
        stu1.setId(100);
        stu1.setName("Kok");
        stu1.setAge(12);
        stu1.setSex("Male");
        stu1.setBatch(111);
        stu1.setAddress("Phnom Penh");
//        stu1.address = "";
        System.out.println("Student 1 ID: " + stu1.getId() + " Name: " + stu1.getName() + " Age: " + stu1.getAge() + " Sex: " + stu1.getSex() + " Batch: " + stu1.getBatch() + " and Address: " + stu1.getAddress());
        stu1.getIdentifier();

        Student stu2 = new Student("Koko", 13, "Male", 110, "Kandal");
        System.out.println("Student 2 ID: " + stu2.getId() + " Name: " + stu2.getName() + " Age: " + stu2.getAge() + " Sex: " + stu2.getSex() + " Batch: " + stu2.getBatch() + " and Address: " + stu2.getAddress());
        stu2.getIdentifier();

        AppleFruitJuice appleFruitJuice = new AppleFruitJuice();
        appleFruitJuice.output();


        Orange orange = new Orange();
        orange.name = "Orange";
        orange.color = "blue";
        orange.id = 1001;
        System.out.println("Orange ID: " + orange.id + " name: " + orange.name + " and color: " + orange.color);
    }
}
