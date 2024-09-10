package com.api.gateway.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


public class AuthenticationFailureHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request,
                         jakarta.servlet.http.HttpServletResponse response,
                         AuthenticationException authException) {

        response.setStatus(401);

    }
}
