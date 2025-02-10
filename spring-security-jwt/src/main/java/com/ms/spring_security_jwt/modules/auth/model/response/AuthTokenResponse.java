package com.ms.spring_security_jwt.modules.auth.model.response;

import com.ms.spring_security_jwt.infrastructure.model.response.BaseResponse;
import com.ms.spring_security_jwt.util.JwtUtil;
import jakarta.persistence.Tuple;
import lombok.Data;

@Data
public class AuthTokenResponse implements BaseResponse {
    private Long id;

    private String token;

    public static AuthTokenResponse toResponse(Tuple tuple) {
        if (tuple == null) return null;

        AuthTokenResponse response = new AuthTokenResponse();
        response.setId(tuple.get("id", Long.class));
        response.setToken(JwtUtil.encryptToken(tuple.get("id", Long.class), tuple.get("username", String.class)));

        return response;
    }
}
