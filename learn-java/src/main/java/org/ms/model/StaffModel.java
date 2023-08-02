package org.ms.model;

public class StaffModel {
    // attributes
    private int id;
    private String name;
    private String position;

    // constructors
    public StaffModel() {}

    public StaffModel(String _name, String _position) {
        id = (int) Math.floor(Math.random() * 10000);
        name = _name;
        position = _position;
    }

    // getter and setter or encapsulation
    public void setId(int _id) {
        id = _id;
    }

    public int getId() {
        return id;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }

    public void setPosition(String _position) {
        position = _position;
    }

    public String getPosition() {
        return position;
    }
}
