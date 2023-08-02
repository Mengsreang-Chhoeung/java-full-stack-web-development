package org.ms.abstract_demo;

public class Student extends Person {
    public Student() {
        super();
    }

    public Student(String _name, String _sex) {
        super(_name, _sex);
    }

    @Override
    public void output(int no) {
        System.out.println("Student " + no + " ID: " + super.getId() + " name: " + super.getName() + " and sex: " + super.getSex());
    }
}
