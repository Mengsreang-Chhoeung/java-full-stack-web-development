package org.ms.inheritance;

// Sub Class
public class AppleFruitJuice extends Juice {
    public void output() {
        super.name = "Apple";
        super.color = "Red";
        super.flavor = "Sweet";
        System.out.println("Fruit name: " + super.name + " color: " + super.color + " and flavor: " + super.flavor);
    }
}
