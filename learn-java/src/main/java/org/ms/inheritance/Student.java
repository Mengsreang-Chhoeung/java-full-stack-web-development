package org.ms.inheritance;

// Sub Class
public class Student extends Person {
    // attribute
    private int batch;

    public Student() {}

    public Student(String _name, int _age, String _sex, int _batch, String _address) {
        super(_name, _age, _sex);
        batch = _batch;
        super.address = _address;
    }

    // encapsulation
    public int getBatch() {
        return batch;
    }

    public void setBatch(int _batch) {
        batch = _batch;
    }

    public String getAddress() {
        return super.address;
    }

    public void setAddress(String _address) {
        super.address = _address;
    }

    public void getIdentifier() {
        super.getIdentifier();
        System.out.println(super.getId() + "-" + super.getName());
    }
}
