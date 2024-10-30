package com.ms.spring_security.module.security.service;

import com.ms.spring_security.model.entity.CustomAuthEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserAuthDetailsService implements UserDetailsService {
    private List<CustomAuthEntity> customAuthEntities = new ArrayList<>();

    public List<CustomAuthEntity> getCustomAuthEntities() {
        return customAuthEntities;
    }

    public void setCustomAuthEntities(List<CustomAuthEntity> customAuthEntities) {
        this.customAuthEntities = customAuthEntities;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String id) {
        UserDetails userDetails = this.loadUserByUsername(id);

        if (userDetails == null) return null;

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(userDetails);

        return usernamePasswordAuthenticationToken;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.getCustomAuthEntities().stream().filter((it) -> Objects.equals(it.getId().toString(), username)).findFirst().orElse(null);
    }
}
