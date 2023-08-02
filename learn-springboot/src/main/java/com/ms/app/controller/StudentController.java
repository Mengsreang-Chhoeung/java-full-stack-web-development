package com.ms.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "key=val")
    public ResponseEntity<String> getStudent(
            @PathVariable("id") Long studentId,
            HttpServletRequest request
    ) {
        System.out.println("Hello header hello: " + request.getHeader("hello"));
        return ResponseEntity.ok("Student ID: " + studentId);
    }
}
