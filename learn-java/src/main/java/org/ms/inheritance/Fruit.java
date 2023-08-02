package org.ms.inheritance;

// Super Class
public class Fruit {
    // attributes
    protected int id;
    protected String name;
    protected String color;

    // constructor
    public Fruit() {}

    public Fruit(String _name, String _color) {
        id = (int) Math.floor(Math.random() * 10000);
        name = _name;
        color = _color;
    }
}
