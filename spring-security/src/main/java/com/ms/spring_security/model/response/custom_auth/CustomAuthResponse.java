package com.ms.spring_security.model.response.custom_auth;

import com.ms.spring_security.infrastructure.model.response.BaseResponse;
import com.ms.spring_security.model.entity.CustomAuthEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomAuthResponse implements BaseResponse {
    private UUID id;
    private List<String> authorities;
    private String username;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public static CustomAuthResponse toResponse(CustomAuthEntity entity) {
        if (entity == null) return null;

        CustomAuthResponse response = new CustomAuthResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setAuthorities(entity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        response.setEnabled(entity.isEnabled());
        response.setAccountNonLocked(entity.isAccountNonLocked());
        response.setAccountNonExpired(entity.isAccountNonExpired());
        response.setCredentialsNonExpired(entity.isCredentialsNonExpired());

        return response;
    }
}
