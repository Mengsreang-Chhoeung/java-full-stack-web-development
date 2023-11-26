package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.model.entity.UserEntity;
import com.ms.crud_api.model.request.user.UserLoginRequest;
import com.ms.crud_api.model.request.user.UserRegisterRequest;
import com.ms.crud_api.model.response.user.UserLoginResponse;
import com.ms.crud_api.model.response.user.UserRegisterResponse;
import com.ms.crud_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) throws Exception {
        UserEntity user = this.userService.register(request);

        return ResponseEntity.ok(UserRegisterResponse.fromEntity(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) throws BadRequestException {
        UserEntity data = this.userService.login(request);

        return ResponseEntity.ok(UserLoginResponse.fromEntity(data));
    }
}
