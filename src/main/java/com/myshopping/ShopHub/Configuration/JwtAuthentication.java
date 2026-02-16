package com.myshopping.ShopHub.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthentication  implements AuthenticationEntryPoint { //important
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String message="Unauthorize ";

        if ("TOKEN_EXPIRED".equals(authException.getMessage())) {
            message = "JWT token has expired";
        } else if ("INVALID_TOKEN".equals(authException.getMessage())) {
            message = "Invalid JWT token";
        } else if (authException instanceof BadCredentialsException) {
            message="invalid Username or password ";
        }

        response.getWriter().write("""
        {
          "status": 401,
          "error": "Unauthorized",
          "message": "%s"
        }
        """.formatted(message));

    }
}