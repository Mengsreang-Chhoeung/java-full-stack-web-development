package org.ms;

public class Main4 {
    static int add(int x, int y) {
        return x + y;
    }

    static double add(double x, double y) {
        return x + y;
    }

    static long add(long x, long y) {
        return x + y;
    }

    public static void main(String[] args) {
        System.out.println("Number 1 + 1 = " + add(1, 1));
        System.out.println("Number 1.0 + 1.0 = " + add(1.0, 1.0));
        System.out.println("Number 10 + 20 = " + add(10L, 20L));
    }
}
