package org.ms.abstract_demo;

public class Main {
    public static void main(String[] args) {
       Person stuKok = new Student();
       stuKok.setId(1001);
       stuKok.setName("Kok");
       stuKok.setSex("Male");
       stuKok.output(1);

       Person stuKoko = new Student();
       stuKoko.setId(1002);
       stuKoko.setName("Koko");
       stuKoko.setSex("Male");
       stuKoko.output(2);
    }
}
