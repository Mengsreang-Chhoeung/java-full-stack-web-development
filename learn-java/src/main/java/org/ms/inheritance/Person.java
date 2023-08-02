package org.ms.inheritance;

// Super Class
public class Person {
    // attributes
    private int id;
    private String name;
    private int age;
    private String sex;
    protected String address;

    // constructors
    public Person() {}

    public Person(String _name, int _age, String _sex) {
        id = (int) Math.floor(Math.random() * 10000);
        name = _name;
        age = _age;
        sex = _sex;
    }

    // encapsulations
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

    public void setAge(int _age) {
        age = _age;
    }

    public int getAge() {
        return age;
    }

    public void setSex(String _sex) {
        sex = _sex;
    }

    public String getSex() {
        return sex;
    }

    public void getIdentifier() {
        System.out.println(id + ":" + name);
    }

//    public final void getIdentifier() {
//        System.out.println(id + ":" + name);
//    }
}
