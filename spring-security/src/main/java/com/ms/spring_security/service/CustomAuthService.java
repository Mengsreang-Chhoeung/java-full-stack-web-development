package com.ms.spring_security.service;

import com.ms.spring_security.exception.BadRequestException;
import com.ms.spring_security.exception.ConflictException;
import com.ms.spring_security.model.entity.CustomAuthEntity;
import com.ms.spring_security.model.request.custom_auth.LoginCustomAuthRequest;
import com.ms.spring_security.model.request.custom_auth.RegisterCustomAuthRequest;
import com.ms.spring_security.module.security.service.UserAuthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomAuthService {
    private final UserAuthDetailsService userAuthDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthService(UserAuthDetailsService userAuthDetailsService, PasswordEncoder passwordEncoder) {
        this.userAuthDetailsService = userAuthDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomAuthEntity login(LoginCustomAuthRequest request) {
        // getting old values
        List<CustomAuthEntity> customAuthEntities = this.userAuthDetailsService.getCustomAuthEntities();

        CustomAuthEntity authEntity = customAuthEntities.stream().filter((it) -> Objects.equals(it.getUsername(), request.getUsername())).findFirst().orElse(null);
        // validate username exists or not
        if (authEntity == null)
            throw new BadRequestException("Incorrect username or password!");

        // validate password
        if (!this.passwordEncoder.matches(request.getPassword(), authEntity.getPassword()))
            throw new BadRequestException("Incorrect username or password!");

        // return result
        return authEntity;
    }

    public CustomAuthEntity register(RegisterCustomAuthRequest request) {
        // getting old values
        List<CustomAuthEntity> customAuthEntities = this.userAuthDetailsService.getCustomAuthEntities();

        // validate username exists or not
        if (customAuthEntities.stream().anyMatch((it) -> Objects.equals(it.getUsername(), request.getUsername())))
            throw new ConflictException("Username already taken, please make a new one!");

        // convert request into entity
        CustomAuthEntity data = request.toEntity(this.passwordEncoder);

        // adding new value
        customAuthEntities.add(data);

        // setting new value
        this.userAuthDetailsService.setCustomAuthEntities(customAuthEntities);

        // return result...
        return data;
    }

    public CustomAuthEntity me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getDetails();

        return this.userAuthDetailsService.getCustomAuthEntities().stream().filter((it) -> Objects.equals(it.getUsername(), userDetails.getUsername())).findFirst().orElse(null);
    }

    // Testing @PostFilter
    @PostFilter("filterObject.equals('kok_koko')")
    public List<String> testPostFilter() {
        List<String> usernames = new ArrayList<>();
        usernames.add("kok_koko");
        usernames.add("kok_dara");
        usernames.add("kok_thyda");

        return usernames;
    }

    // Testing @PreFilter
    // for a single argument
    @PreFilter("filterObject.equals('kok_koko')")
    // for multiple arguments
    // @PreFilter(value = "filterObject.equals('kok_koko')", filterTarget = "usernames")
    public String testPreFilter(List<String> usernames) {
        return String.join(";", usernames);
    }
}
