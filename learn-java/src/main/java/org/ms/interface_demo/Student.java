package org.ms.interface_demo;

public class Student implements Person {
    @Override
    public void output() {
        System.out.println("Hello I'm Student!");
    }

    @Override
    public void favoriteFood() {
        System.out.println("My Favorite Food is Kari.");
    }

    @Override
    public void favoriteSport() {
        System.out.println("My Favorite Sport is Football.");
    }
}
