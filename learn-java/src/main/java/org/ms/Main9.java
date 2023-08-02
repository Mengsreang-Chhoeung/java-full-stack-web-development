package org.ms;

//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Optional;
//import java.util.List;
//import java.util.*;
//import org.ms.service.CustomerService;
//import org.ms.service.StaffService;
//import org.ms.service.StudentService;
//import org.ms.service.TeacherService;
import org.ms.service.*;

public class Main9 {
    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println("Hello Date: " + date);
//        Optional<String> opt = Optional.empty();
//        List<String> list = new ArrayList<>();
//        String uuid = UUID.randomUUID().toString();

        CustomerService customerService = new CustomerService();
        StaffService staffService = new StaffService();
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        customerService.greeting();
        staffService.greeting();
        studentService.greeting();
        teacherService.greeting();
        org.ms.service2.CustomerService customerService2 = new org.ms.service2.CustomerService();
        customerService2.greeting();
    }
}
