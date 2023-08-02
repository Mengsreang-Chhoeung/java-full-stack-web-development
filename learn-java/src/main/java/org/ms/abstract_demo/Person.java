package org.ms.abstract_demo;

// Abstract Class
public abstract class Person {
    // attributes
    private int id;
    private String name;
    private String sex;

    // default constructor
    public Person() {}

    public Person(String _name, String _sex) {
        this.id = (int) Math.floor(Math.random() * 10000);
        this.name = _name;
        this.sex = _sex;
    }

    // Abstract Method
    public abstract void output(int no);

    // Getter and Setter
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

    public String getSex() {
        return this.sex;
    }

    public void setSex(String _sex) {
        this.sex = _sex;
    }
}
