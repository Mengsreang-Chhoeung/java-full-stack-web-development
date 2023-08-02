package com.ms.app.model;

import com.ms.app.service.TeacherService;

public class JavaTeacher extends Teacher {
    // attributes
    private TeacherService teacherService;

    public JavaTeacher(TeacherService _teacherService) {
        this.teacherService = _teacherService;
    }

    public JavaTeacher() {
        super();
    }

    public JavaTeacher(String _name, String _subject) {
        super(_name, _subject);
    }

    @Override
    public void output() {
//        System.out.println("I'm a Java Teacher and ID: " + super.getId() + " name: " + super.getName() + " and subject: " + super.getSubject());
        this.teacherService.output(this);
    }
}
