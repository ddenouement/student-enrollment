package com.example.studentenrollment.security;

import com.example.studentenrollment.error.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Date;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(401);

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message(authException.getMessage())
                .statusCode(401)
                .timestamp(new Date().getTime())
                .path(request.getServletPath())
                .build();

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), error);
    }

}
