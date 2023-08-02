package org.ms.interface_demo;

public class Teacher implements Person, PersonBehaviour {
    @Override
    public void output() {
        System.out.println("Hello I'm Teacher!");
    }

    @Override
    public void favoriteFood() {
        System.out.println("My Favorite Food is Kokur.");
    }

    @Override
    public void favoriteSport() {
        System.out.println("My Favorite Sport is Tennis.");
    }

    @Override
    public void getBehaviour() {
        System.out.println("I Like Travelling.");
    }
}
