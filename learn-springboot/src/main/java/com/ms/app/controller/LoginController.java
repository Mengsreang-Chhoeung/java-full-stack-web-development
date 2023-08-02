package com.ms.app.controller;

import com.ms.app.model.request.LoginFormRequest;
import com.ms.app.model.response.LoginFormResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginFormResponse> login(@RequestBody LoginFormRequest loginFormRequest) {
        LoginFormResponse response = new LoginFormResponse();
        response.username = loginFormRequest.username.toUpperCase();

        return ResponseEntity.ok(response);
    }
}
