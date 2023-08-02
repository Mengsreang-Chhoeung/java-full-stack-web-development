package org.ms.inner_demo;

// Outer Class
public class Student {
    // attributes
    public int id;
    public String name;
    public static int totalSubject = 10;

    // Inner Class
    public class StudentDetails {
        // attributes
        public String school;
    }

     static class StudentSubject {
        public String name;

        public int getTotal() {
            return totalSubject;
        }
    }
}
