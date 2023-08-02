package org.ms;

public class Main7 {
    public static void main(String[] args) {
        Customer customerKok = new Customer();
        System.out.println("Customer 1 ID: " + customerKok.id);
        System.out.println("Customer 1 Name: " + customerKok.name);
        System.out.println("Customer 1 Sex: " + customerKok.sex);
        System.out.println("Customer 1 Phone Number: " + customerKok.phoneNumber);
        System.out.print("\n");

        Customer customerKoko = new Customer("Koko", "Male", "855-321654987");
        System.out.println("Customer 2 ID: " + customerKoko.id);
        System.out.println("Customer 2 Name: " + customerKoko.name);
        System.out.println("Customer 2 Sex: " + customerKoko.sex);
        System.out.println("Customer 2 Phone Number: " + customerKoko.phoneNumber);
        System.out.print("\n");

        Customer customerTola = new Customer("Tola", "Male", 456789123);
        System.out.println("Customer 3 ID: " + customerTola.id);
        System.out.println("Customer 3 Name: " + customerTola.name);
        System.out.println("Customer 3 Sex: " + customerTola.sex);
        System.out.println("Customer 3 Phone Number: " + customerTola.phoneNumber);
    }
}
