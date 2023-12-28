package com.zsc.edu.bill.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsc.edu.bill.exception.ExceptionResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author harry_yao
 */
@AllArgsConstructor
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=utf-8");
        ExceptionResult result = new ExceptionResult(exception.getMessage(), HttpStatus.UNAUTHORIZED.value(),
            LocalDateTime.now());
        response.getWriter().print(objectMapper.writeValueAsString(result));
        response.flushBuffer();
    }

}

