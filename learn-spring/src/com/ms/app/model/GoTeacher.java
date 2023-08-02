package com.ms.app.model;

import com.ms.app.service.TeacherService;

public class GoTeacher extends Teacher {
    // attributes
    private TeacherService teacherService;

    // Constructors
    public GoTeacher() {
        super();
    }

    public GoTeacher(String _name, String _subject) {
        super(_name, _subject);
    }

    public GoTeacher(TeacherService _teacherService) {
        this.teacherService = _teacherService;
    }

    @Override
    public void output() {
        this.teacherService.output(this);
    }
}
