package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.UserEntity;
import com.ms.crud_api.model.request.user.UserRegisterRequest;
import com.ms.crud_api.model.response.user.UserRegisterResponse;
import com.ms.crud_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserRegisterResponse>> findAll() {
        List<UserRegisterResponse> data = this.userService.findAll().stream().map(UserRegisterResponse::fromEntity).toList();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRegisterResponse> findOne(@PathVariable Long id) throws NotFoundException {
        UserEntity data = this.userService.findOne(id);

        return ResponseEntity.ok(UserRegisterResponse.fromEntity(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRegisterResponse> update(@PathVariable Long id, @RequestBody UserRegisterRequest request) throws Exception {
        UserEntity data = this.userService.update(id, request);

        return ResponseEntity.ok(UserRegisterResponse.fromEntity(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Delete Successful");
        this.userService.delete(id);

        return ResponseEntity.ok(response);
    }
}
