package com.ms.spring_security_jwt.modules.security.model;

import com.ms.spring_security_jwt.constant.enums.UserRoleEnum;
import com.ms.spring_security_jwt.modules.security.model.projection.UserAuthDetailsProjection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public record UserAuthDetails(UserAuthDetailsProjection user) implements UserDetails {
    public Long getId() {
        return this.user.id();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + this.user.role().name());

        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public UserRoleEnum getRole() {
        return this.user.role();
    }

    @Override
    public String getPassword() {
        return this.user().password();
    }

    @Override
    public String getUsername() {
        return this.user().username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user().enabled();
    }
}
