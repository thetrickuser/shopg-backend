package com.shopg.userservice.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (request.getRequestURI().contains("admin")) {
            response.getWriter().write("{\"error\": \"Access denied. Only admin users can access this endpoint.\"}");
        } else if (request.getRequestURI().contains("user")) {
            response.getWriter().write("{\"error\": \"Access denied. Only non-admin users can access this endpoint.\"}");
        }
    }
}
