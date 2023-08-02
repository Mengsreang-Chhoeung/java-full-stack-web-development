package com.ms.app.model;

import com.ms.app.service.TeacherService;

public class RustTeacher extends Teacher {
    // attributes
    private TeacherService teacherService;

    // Constructors
    public RustTeacher() {
        super();
    }

    public RustTeacher(String _name, String _subject) {
        super(_name, _subject);
    }

    public RustTeacher(TeacherService _teacherService) {
        this.teacherService = _teacherService;
    }

    public void initMethod() {
        super.setId((int) Math.floor(Math.random() * 10000));
        super.setName("Kok Koko");
        super.setSubject("Rust 2");
    }

    public void destroyMethod() {
        super.setId(0);
        super.setName(null);
        super.setSubject(null);
    }

    @Override
    public void output() {
        this.teacherService.output(this);
    }
}
