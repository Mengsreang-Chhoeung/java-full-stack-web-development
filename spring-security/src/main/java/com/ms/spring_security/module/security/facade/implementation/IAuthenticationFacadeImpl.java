package com.ms.spring_security.module.security.facade.implementation;

import com.ms.spring_security.module.security.facade.IAuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class IAuthenticationFacadeImpl implements IAuthenticationFacade {
    @Override
    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
