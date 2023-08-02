package org.ms;

public class Main3 {
    // define a method
//    static void getGreeting() {
//        System.out.println("Hello Java");
//    }

//    static void getFullName(String firstName, int age) {
//        String fName;
//        if (firstName == null) fName = "Dara";
//        else fName = firstName;
//
//        System.out.println("Kok " + fName + " and age: " + age + " years old.");
//    }

    static int add(int x, int y) {
        if (x == 0 && y == 0) {
            return 1 + 1;
        }

        return x + y;
    }

    public static void main(String[] args) {
        System.out.println("Number 1 + 2 = " + add(1, 2));
        System.out.println("Number 2 + 2 = " + add(2, 2));
        System.out.println("Number 0 + 0 = " + add(0, 0));


//        getFullName(null, 20);
//        getFullName("Tola", 30);
//        getFullName("Seyha", 40);
//        getFullName("Bunna", 60);

        // invoke or call functions
//        getGreeting();
//        getGreeting();
//        getGreeting();
//        getGreeting();
//        getGreeting();
//        getGreeting();
//        getGreeting();
//        getGreeting();
    }
}
