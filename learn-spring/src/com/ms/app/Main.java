package com.ms.app;

import com.ms.app.model.JavaTeacher;
import com.ms.app.model.Teacher;

public class Main {
    public static void main(String[] args) {
        Teacher javaTeacher = new JavaTeacher("Kok", "Java");
        javaTeacher.output();
    }
}