package com.ms.spring_security.controller;

import com.ms.spring_security.module.security.facade.IAuthenticationFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    private final IAuthenticationFacade iAuthenticationFacade;

    @Autowired
    public IndexController(IAuthenticationFacade iAuthenticationFacade) {
        this.iAuthenticationFacade = iAuthenticationFacade;
    }

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Index");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/get-user-info")
    public ResponseEntity<String> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(authentication.getName());
    }

    @GetMapping("/get-user-info2")
    public ResponseEntity<String> getUserInfo2(
//            Principal principal
//            Authentication authentication
            HttpServletRequest request
    ) {
//        return ResponseEntity.ok(principal.getName());

//        return ResponseEntity.ok(authentication.getName());

        return ResponseEntity.ok(request.getUserPrincipal().getName());
    }

    @GetMapping("/get-user-info3")
    public ResponseEntity<String> getUserInfo3() {
        System.out.println("Hello Authorities: " + this.iAuthenticationFacade.authentication().getAuthorities());

        return ResponseEntity.ok(this.iAuthenticationFacade.authentication().getName());
    }
}
