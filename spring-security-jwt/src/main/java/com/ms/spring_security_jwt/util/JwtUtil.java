package com.ms.spring_security_jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;
import java.util.Date;

public final class JwtUtil {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "bearer ";
    private final static String SECRET_KEY = "spring@security1spring@security1";
    private final static Long EXPIRE_DATE = 604800000L; // expire in 1 week

    private static SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String extractToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null)
            header = "";
        boolean isBearerToken = header.trim().toLowerCase().startsWith(TOKEN_PREFIX);
        if (!isBearerToken)
            return null;

        String token = header.substring(TOKEN_PREFIX.length());
        boolean isValidJwtThreePart = token.split("\\.").length == 3;
        if (!isValidJwtThreePart)
            return null;

        return token;
    }

    public static String encryptToken(Long id, String username) {
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + EXPIRE_DATE);

        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .claim("id", id)
                .subject(username)
                .issuedAt(new Date())
                .expiration(expiration)
                .issuer(username)
                .signWith(getKey())
                .compact();
    }

    public static Claims decryptToken(String token) {
        if (token == null)
            return null;

        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }
}
