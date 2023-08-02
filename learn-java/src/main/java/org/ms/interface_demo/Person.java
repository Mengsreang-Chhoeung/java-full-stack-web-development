package org.ms.interface_demo;

public interface Person {
    // methods
    void output();

    void favoriteFood();

    void favoriteSport();

    private int getRandomAge() {
        return (int) Math.floor(Math.random() * 100);
    }

    private String getDefaultName() {
        return "John Doe";
    }
}
