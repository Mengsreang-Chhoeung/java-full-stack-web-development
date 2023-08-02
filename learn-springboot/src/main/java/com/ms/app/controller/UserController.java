package com.ms.app.controller;

import com.ms.app.model.entity.UserEntity;
import com.ms.app.model.request.user.CreateUserRequest;
import com.ms.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository _userRepository) {
        this.userRepository = _userRepository;
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody CreateUserRequest req) {
        UserEntity userReq = new UserEntity();
        userReq.setUsername(req.username);
        userReq.setContact(req.contact);
        UserEntity user = this.userRepository.save(userReq);

        return ResponseEntity.ok(user);
    }
}
