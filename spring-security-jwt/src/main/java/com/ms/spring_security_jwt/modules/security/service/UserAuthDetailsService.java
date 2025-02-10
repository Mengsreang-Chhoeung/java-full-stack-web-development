package com.ms.spring_security_jwt.modules.security.service;

import com.ms.spring_security_jwt.infrastructure.exception.NotFoundException;
import com.ms.spring_security_jwt.infrastructure.exception.UnauthorizedException;
import com.ms.spring_security_jwt.modules.security.model.UserAuthDetails;
import com.ms.spring_security_jwt.modules.security.model.projection.UserAuthDetailsProjection;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import com.ms.spring_security_jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAuthDetailsService implements UserDetailsService {
    private final EntityManager entityManager;

    private Optional<UserAuthDetailsProjection> getUserByUsername(String username) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserAuthDetailsProjection> criteriaQuery = builder.createQuery(UserAuthDetailsProjection.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("username").alias("username"),
                root.get("password").alias("password"),
                root.get("enabled").alias("enabled"),
                root.get("role").alias("role")
        ).where(builder.equal(root.get("username"), username), builder.isNull(root.get("deletedAt")));

        TypedQuery<UserAuthDetailsProjection> query = this.entityManager.createQuery(criteriaQuery);

        List<UserAuthDetailsProjection> result = query.getResultList();
        if (result.size() > 0) return Optional.of(result.get(0));
        else return Optional.empty();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = JwtUtil.decryptToken(token);
        if (claims == null) return null;

        String username = claims.getSubject();
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) return null;
        if (!userDetails.isEnabled())
            throw new UnauthorizedException("User is not enabled!");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(userDetails);

        return usernamePasswordAuthenticationToken;
    }

    public UserAuthDetails getCurrentUser() {
        return this.getCurrentUserOrNull().orElseThrow(() -> new NotFoundException("User not found!"));
    }

    public Optional<UserAuthDetails> getCurrentUserOrNull() {
        // 1. Get the authentication information from SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2. check some conditions and then return data
        if (authentication == null)
            return Optional.empty();
        else if (authentication.getDetails() instanceof UserAuthDetails)
            return Optional.of((UserAuthDetails) authentication.getDetails());
        else
            return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthDetailsProjection user = this.getUserByUsername(username).orElse(null);

        if (user != null) {
            return new UserAuthDetails(user);
        }

        return null;
    }
}
