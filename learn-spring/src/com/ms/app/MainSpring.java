package com.ms.app;

import com.ms.app.model.Teacher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainSpring {
    public static void main(String[] args) {
        /**
         * load the spring configuration file
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        /**
         * retrieve beans
         */
//        Teacher javaTeacher = context.getBean("javaTeacher", Teacher.class);
//        Teacher pythonTeacher = context.getBean("pythonTeacher", Teacher.class);
//        Teacher jvTeacher = context.getBean("jvTeacher", Teacher.class);
        Teacher rustTeacher = context.getBean("rustTeacher", Teacher.class);
        Teacher rusTeacher = context.getBean("rustTeacher", Teacher.class);
        Teacher ruTeacher = context.getBean("rustTeacher", Teacher.class);
//        Teacher goTeacher = context.getBean("goTeacher", Teacher.class);
//        Teacher gTeacher = context.getBean("goTeacher", Teacher.class);

        /**
         * set value to beans
         */
//        javaTeacher.setId(101);
//        javaTeacher.setName("Kok168");
//        javaTeacher.setSubject("Java Spring");
//        rustTeacher.setId(108);
//        rustTeacher.setName("Kok Tola");
//        rustTeacher.setSubject("Rust");
//        goTeacher.setId(209);
//        goTeacher.setName("Kok Seyha");
//        goTeacher.setSubject("Go");
//        gTeacher.setId(210);
//        gTeacher.setName("Kok Bopha");
//        gTeacher.setSubject("Go");

        /**
         * output beans
         */
//        javaTeacher.output();
//        pythonTeacher.output();
//        System.out.println("I'm a " + jvTeacher.getSubject() + " Teacher and ID: " + jvTeacher.getId() + " name: " + jvTeacher.getName() + " and subject: " + jvTeacher.getSubject());
        rustTeacher.output();
        rusTeacher.output();
        ruTeacher.output();
//        goTeacher.output();
//        gTeacher.output();

        /**
         * close context
         */
        context.close();
    }
}
