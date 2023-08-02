package org.ms.inheritance;

public class Teacher extends Person {
    // Constructors
    public Teacher() {}

    public Teacher(String _name, int _age, String _sex) {
        super(_name, _age, _sex);
    }

    // Methods
    public void getIdentifier() {
        super.getIdentifier();
        System.out.println("Welcome new Teacher!");
    }
}
