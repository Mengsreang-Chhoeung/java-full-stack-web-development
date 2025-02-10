package com.ms.spring_security_jwt.modules.auth.service;

import com.ms.spring_security_jwt.modules.auth.model.request.BackendAuthLoginRequest;
import com.ms.spring_security_jwt.modules.auth.model.request.FrontendAuthLoginRequest;
import com.ms.spring_security_jwt.modules.auth.model.response.AuthTokenResponse;
import com.ms.spring_security_jwt.modules.user.model.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthTokenResponse backendLogin(BackendAuthLoginRequest request);

    AuthTokenResponse frontendLogin(FrontendAuthLoginRequest request);

    UserResponse ownInfo();
}
