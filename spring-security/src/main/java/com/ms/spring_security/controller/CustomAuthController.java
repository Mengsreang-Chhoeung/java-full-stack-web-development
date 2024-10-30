package com.ms.spring_security.controller;

import com.ms.spring_security.constant.RestURIConstant;
import com.ms.spring_security.infrastructure.model.response.body.BaseBodyResponse;
import com.ms.spring_security.model.entity.CustomAuthEntity;
import com.ms.spring_security.model.request.custom_auth.LoginCustomAuthRequest;
import com.ms.spring_security.model.request.custom_auth.RegisterCustomAuthRequest;
import com.ms.spring_security.model.response.custom_auth.CustomAuthResponse;
import com.ms.spring_security.service.CustomAuthService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = RestURIConstant.CUSTOM_AUTH)
public class CustomAuthController {
    private final CustomAuthService customAuthService;

    @Autowired
    public CustomAuthController(CustomAuthService customAuthService) {
        this.customAuthService = customAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseBodyResponse> register(
            @RequestBody RegisterCustomAuthRequest request
    ) {
        CustomAuthEntity data = this.customAuthService.register(request);

        return BaseBodyResponse.success(CustomAuthResponse.toResponse(data), null);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseBodyResponse> login(
            @RequestBody LoginCustomAuthRequest request
    ) {
        CustomAuthEntity data = this.customAuthService.login(request);

        return BaseBodyResponse.success(CustomAuthResponse.toResponse(data), null);
    }

    @GetMapping("/me")
    public ResponseEntity<BaseBodyResponse> me() {
        CustomAuthEntity data = this.customAuthService.me();

        return BaseBodyResponse.success(CustomAuthResponse.toResponse(data), null);
    }

    @GetMapping("/admin")
    public ResponseEntity<BaseBodyResponse> admin() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/deny-all")
    public ResponseEntity<BaseBodyResponse> denyAll() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/anonymous")
    public ResponseEntity<BaseBodyResponse> anonymous() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/authenticated")
    public ResponseEntity<BaseBodyResponse> authenticated() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/a")
    public ResponseEntity<BaseBodyResponse> a() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/a/a")
    public ResponseEntity<BaseBodyResponse> a_a() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/a/b")
    public ResponseEntity<BaseBodyResponse> a_b() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/a/b/c")
    public ResponseEntity<BaseBodyResponse> a_b_c() {
        return BaseBodyResponse.success(null, null);
    }

    @GetMapping("/a/b/c/d")
    public ResponseEntity<BaseBodyResponse> a_b_c_d() {
        return BaseBodyResponse.success(null, null);
    }

//    @Secured({"USERS", "ADMIN", "SUPPLIERS"})
//    @RolesAllowed({"USERS", "ADMIN", "SUPPLIERS"})
//    @PostAuthorize("hasAuthority('USER') and hasAuthority('SUPPLIERS')")
    @PostAuthorize("returnObject.statusCodeValue == 201")
    @GetMapping("/msa")
    public ResponseEntity<BaseBodyResponse> methodSecurityA() {
        return BaseBodyResponse.success(null,  null);
    }

    @GetMapping("/msb")
    public ResponseEntity<List<String>> methodSecurityB() {
        return ResponseEntity.ok(this.customAuthService.testPostFilter());
    }

    @GetMapping("/msc")
    public ResponseEntity<String> methodSecurityC() {
        List<String> usernames = new ArrayList<>();
        usernames.add("kok_koko");
        usernames.add("kok_dara");
        usernames.add("kok_thyda");

        return ResponseEntity.ok(this.customAuthService.testPreFilter(usernames));
    }
}
