package org.ms;

public class Teacher {
    // attributes
    private int id;
    private String name;

    // Constructors
    public Teacher() {}

    public Teacher(String _name) {
        this.id = (int) Math.floor(Math.random() * 10000);
        this.name = _name;
    }

    // Encapsulations
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

    // methods
    private String getIdentifier() {
        return "ID: " + this.getId() + " and Name: " + this.getName();
    }

    public void output(int no) {
        System.out.println("Teacher " + no + " " + this.getIdentifier());
    }
}
