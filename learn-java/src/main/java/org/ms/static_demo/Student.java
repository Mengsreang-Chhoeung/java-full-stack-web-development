package org.ms.static_demo;

public class Student {
    // instance attributes
    private int id;
    private String name;

    // static attributes
    public static String school;

    // static methods
    public static void outputSchool() {
        Student tola = new Student();
        tola.setId(103);
        tola.setName("Tola");
        System.out.println("Welcome to " + school + " School!");
    }

    public void outputStudent() {
        school = "";
        outputSchool();
    }

    // instance methods
    public int getId() {
        return this.id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String _name) {
        this.name = _name;
    }
}
