package org.ms;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        double a, b;
//        double value;
//        System.out.println("Welcome to our exam!");
//        System.out.print("Please enter a: ");
//        a = input.nextDouble();
//        System.out.print("Please enter b: ");
//        b = input.nextDouble();

//        value = a + b;
//        value = a - b;
//        value = a * b;
//        value = a / b;
//        value = a % b;
//        a++;
//        ++a;
//        b--;
//        --b;

//        System.out.println("A = " + a + " and B = " + b + ", So value is equal to " + value);
//        System.out.println("A = " + a);
//        System.out.println("B = " + b);


//        int a = 30, b = 20;
//        System.out.println("A = " + a + "\tB = " + b);
//        if (!(a > 50 && b > 50)) {
//            System.out.println("A and B are not bigger than 50!");
//        }

//        boolean correct = false;
//        if (correct == false) {
//            System.out.println("Hello correct equal to false");
//        }
//        if (!correct) {
//            System.out.println("Hello correct equal to false");
//        }


//        System.out.println("Hello Maximum: " + Math.max(10, 20));
//        System.out.println("Hello Minimum: " + Math.min(10, 20));
//        System.out.println("Hello Square Root: " + Math.sqrt(16));
//        System.out.println("Hello Absolute: " + Math.abs(-19.56));
//        System.out.println("Hello Floor: " + Math.floor(5.5));
//        System.out.println("Hello Round: " + Math.round(5.5));
//        System.out.println("Hello Random: " + Math.random() * 100);


//        String greeting = "Hello";
//        System.out.println("Hello length of greeting: " + greeting.length());
//        System.out.println("Hello greeting in upper case: " + greeting.toUpperCase());
//        System.out.println("Hello greeting in lower case: " + greeting.toLowerCase());
//
//        String greetingText = "Hello World From World";
//        System.out.println("Hello Index of World: " + greetingText.indexOf("World", 7));
//
//        String firstName = "John";
//        String lastName = "Doe";
//        System.out.println("Hello Full Name 1: " + firstName + " " + lastName);
//        System.out.println("Hello Full Name 2: " + firstName.concat(" ").concat(lastName).concat(" Hello"));
//
//        System.out.println("Hello \"World\"");
//        System.out.println("Hello 'World'");
//        System.out.println("Hello \\\\ World");
//        System.out.println("Hello \nWorld");
//        System.out.println("Hello \t World");


//        if (20 < 25) {
//            System.out.println("20 is bigger than 25");
//        }
//
//        if (20 < 18) {
//            System.out.println("20 is bigger than 18");
//        } else {
//            System.out.println("18 is smaller than 20");
//        }
//
//        if (10 > 30) {
//            System.out.println("10 is bigger than 30");
//        } else if (20 > 30) {
//            System.out.println("20 is bigger than 30");
//        } else {
//            System.out.println("30 is bigger then 20 and 10");
//        }
//
//        String result = (20 > 18) ? ((20 > 19) ? "Good Day" : "Best Day") : "Good Evening";
//        System.out.println("Hello " + result);
//
//        int day = 4;
//        switch (day) {
//            case 6:
//                System.out.println("Hello Saturday");
//                System.out.println("Hello World");
//                break;
//            case 7:
//                System.out.println("Hello Sunday");
//                break;
//
//            default:
//                System.out.println("Invalid day!");
//        }


        // infinite loop
//        while(true) {
//            System.out.println("hello world");
//        }

        // while loop
//        int i = 0;
//        while (i < 5) {
//            System.out.println("Hello " + i);
//            i++;
//        }

        // do...while loop
//        int i = 0;
//        do {
//            System.out.println("Hello " + i);
//            i++;
//        } while (i < 5);

        // for loop
//        for (int i = 0; i < 5; i++) {
//            System.out.println("Hello ForLoop1 " + i);
//        }
//
//        int i = 0;
//        for (; i < 5;) {
//            System.out.println("Hello ForLoop2 " + i);
//            i++;
//        }

        // using break keyword
//        int i = 0;
//        while (i < 5) {
//            if (i == 2) break;
//            System.out.println("Hello " + i);
//            i++;
//        }

        // using continue keyword
//        int i = 0;
//        while (i < 5) {
//            if (i == 2) {
//                i++;
//                continue;
//            }
//            System.out.println("Hello " + i);
//            i++;
//        }

//        long[] myNumbers = {1, 3, 4, 5, 6, 6};
//        System.out.println("Hello Number: " + myNumbers[3]);

        // modify more
//        myNumbers[5] = 10;

//        System.out.println("Hello Number: " + myNumbers[5]);
//
//        System.out.println("Hello My Number Length: " + myNumbers.length);
//
//        System.out.println("Hello Last Number: " + myNumbers[myNumbers.length - 1]);

//        for (int i = 0; i < myNumbers.length; i++) {
//            System.out.println("Hello Number: " + myNumbers[i]);
//        }

//        int index = 0;
//        for (long i : myNumbers) {
//            System.out.println("Hello Number: " + (index++));
//        }

        int[][] myNumbers = {{1, 2, 3, 4}, {5, 6, 7}, {}};
        System.out.println("Hello Number: " + myNumbers.length);
        System.out.println("Hello Number in Array: " + myNumbers[0].length);
        System.out.println("Hello Number 3: " + myNumbers[0][2]);
        System.out.println("Hello Number 6: " + myNumbers[1][1]);

        // modify
        myNumbers[0][2] = 30;
        System.out.println("Hello Number 3: " + myNumbers[0][2]);

        for (int i = 0; i < myNumbers.length; i++) {
            for (int j = 0; j < myNumbers[i].length; j++) {
                System.out.println("Hello Number: " + myNumbers[i][j]);
            }
        }

    }
}
