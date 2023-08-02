package org.ms;

public class Main5 {
    int x = 5;

    public static void main(String[] args) {
        Main5 myObj = new Main5();
        Main5 myObj2 = new Main5();
        Main5 myObj3 = new Main5();
        myObj2.x = 10;
        myObj3.x = 20;
        System.out.println("Hello X: " + myObj.x);
        System.out.println("Hello X2: " + myObj2.x);
        System.out.println("Hello X3: " + myObj3.x);
    }
}
