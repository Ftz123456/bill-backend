package com.zsc.edu.bill.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsc.edu.bill.exception.ExceptionResult;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author harry_yao
 */
@AllArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ex) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ExceptionResult result;
        if (ex instanceof MissingCsrfTokenException) {
            System.out.println("MissingCsrfTokenException");
            // 会话已注销，返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            result = new ExceptionResult("凭证已过期，请重新登录", HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now());
        } else {
            // 403
            response.setStatus(HttpStatus.FORBIDDEN.value());
            result = new ExceptionResult("禁止操作", HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now());
        }
        response.getWriter().print(objectMapper.writeValueAsString(result));
        response.flushBuffer();
    }
}
