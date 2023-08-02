package com.ms.app.model;

import com.ms.app.service.TeacherService;

public class PythonTeacher extends Teacher {
    // attributes
    private TeacherService teacherService;

    // methods
    public void setTeacherService(TeacherService _teacherService) {
        this.teacherService = _teacherService;
    }

    @Override
    public void output() {
        teacherService.output(this);
    }
}
