package com.ms.app.service.implementation;

import com.ms.app.model.Teacher;
import com.ms.app.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
    @Override
    public void output(Teacher _teacher) {
        System.out.println("I'm a " + _teacher.getSubject() + " Teacher and ID: " + _teacher.getId() + " name: " + _teacher.getName() + " and subject: " + _teacher.getSubject());
    }
}
