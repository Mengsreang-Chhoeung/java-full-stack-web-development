package com.ms.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController {
    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> handleErrorPage() {
        Map<String, Object> error = new HashMap<>();
        error.put("error", 404);
        error.put("message", "Not Found!");

        return ResponseEntity.status(404).body(error);
    }
}
