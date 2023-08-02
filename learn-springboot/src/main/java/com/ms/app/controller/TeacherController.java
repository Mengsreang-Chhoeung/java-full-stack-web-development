package com.ms.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @GetMapping
    public String getTeacher(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String subject
    ) {
        return "Teacher ID: " + id + " and Name: " + name + " and Subject: " + subject;
    }

    @GetMapping("/two")
    public String getTeacherTwo(
            @RequestParam("_id") Long teacherId,
            @RequestParam("_name") String teacherName,
            @RequestParam("_subject") String teacherSubject
    ) {
        return "Teacher ID: " + teacherId + " and Name: " + teacherName + " and Subject: " + teacherSubject;
    }

    @GetMapping("/three")
    public String getTeacherThree(
            @RequestParam(value = "_id", required = false) Long teacherId,
            @RequestParam("_name") String teacherName,
            @RequestParam("_subject") String teacherSubject
    ) {
        long getTeacherId;
        if (teacherId == null) {
            getTeacherId = (long) Math.floor(Math.random() * 10000);
        } else {
            getTeacherId = teacherId;
        }
        return "Teacher ID: " + getTeacherId + " and Name: " + teacherName + " and Subject: " + teacherSubject;
    }

    @GetMapping("/four")
    public String getTeacherFour(
            @RequestParam(value = "_id", required = false) Optional<Long> teacherId,
            @RequestParam("_name") String teacherName,
            @RequestParam("_subject") String teacherSubject
    ) {
        long getTeacherId = teacherId.orElse((long) Math.floor(Math.random() * 10000));
        return "Teacher ID: " + getTeacherId + " and Name: " + teacherName + " and Subject: " + teacherSubject;
    }

    @GetMapping("/five")
    public String getTeacherFive(
            @RequestParam(value = "_id", required = false, defaultValue = "168") Long teacherId,
            @RequestParam("_name") String teacherName,
            @RequestParam("_subject") String teacherSubject
    ) {
        return "Teacher ID: " + teacherId + " and Name: " + teacherName + " and Subject: " + teacherSubject;
    }

    @GetMapping("/six")
    public String getTeacherSix(
            @RequestParam Map<String, Object> params
    ) {
        return "Teacher ID: " + params.getOrDefault("_id", "168") + " and Name: " + params.getOrDefault("_name", "Kok") + " and Subject: " + params.getOrDefault("_subject", "Java");
    }

    @GetMapping("/seven")
    public String getTeacherSeven(
            @RequestParam(required = false, defaultValue = "1,2,3") List<Long> id
    ) {
        return "Teacher ID: " + id;
    }
}
