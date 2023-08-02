package org.ms;

public class Customer {
    // attributes
    int id;
    String name;
    String sex;
    String phoneNumber;
//    final String occupation;

    // default constructor
    Customer() {
        id = 1;
        name = "Kok";
        sex = "Male";
        phoneNumber = "855-123456789";
//        occupation = "Police";
        System.out.println("Hello from default constructor...");
    }

    // parameterized constructor
    Customer(String _name, String _sex, String _phoneNumber) {
        id = (int) Math.floor(Math.random() * 1000);
        name = _name;
        sex = _sex;
        phoneNumber = _phoneNumber;
//        occupation = "Police";
        System.out.println("Hello from parameterized constructor 1...");
    }

    Customer(String _name, String _sex, int _phoneNumber) {
        id = (int) Math.floor(Math.random() * 1000);
        name = _name;
        sex = _sex;
        phoneNumber = "855-" + _phoneNumber;
//        occupation = "Police";
        System.out.println("Hello from parameterized constructor 2...");
    }
}
