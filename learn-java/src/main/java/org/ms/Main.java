package org.ms;

public class Main {
    public static void main(String[] args) {
        String greeting = "Hello world!";
        int number = 100;
        final double amount = 1.5;
//        System.out.println(greeting + " " + number);
//        System.out.println("Amount: " + amount);
        greeting = "Hello Java!";
        number = 200;
//        amount = 1.6;
//        System.out.println(greeting + " " + number);


        double amount2 = 13.888888888888888888;
//        System.out.println("Hello Amount 2: " + amount2);

        // Type Casting
        int a = 10;
        // Widening Casting
        double b = a;
        System.out.println("Hello A: " + a);
        System.out.println("Hello B: " + b);

        // Narrowing Casting
        float c = 1.5f;
        short d = (short) c;
        System.out.println("Hello C: " + c);
        System.out.println("Hello D: " + d);

        // Convert from int to String
        int e = 123;
        String f = String.valueOf(e); // "123"
        System.out.println("Hello F: " + f);

        // Convert from String to double
        double g = Double.parseDouble(f);
        System.out.println("Hello G: " + g);

        // Convert from String to long
        long ll = Long.parseLong(f);
        System.out.println("Hello LL: " + ll);

        // Convert from String to boolean
        boolean bol = Boolean.parseBoolean("false");
        System.out.println("Hello Bol: " + bol);
    }
}