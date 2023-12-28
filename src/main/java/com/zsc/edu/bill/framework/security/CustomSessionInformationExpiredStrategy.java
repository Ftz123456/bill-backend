package com.zsc.edu.bill.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsc.edu.bill.exception.ExceptionResult;
import com.zsc.edu.bill.framework.SpringBeanUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author harry_yao
 */
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        ObjectMapper objectMapper = SpringBeanUtil.getBean(ObjectMapper.class);
        HttpServletResponse response = event.getResponse();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=utf-8");
        ExceptionResult result = new ExceptionResult("会话已过期（有可能是您同时登录了太多的太多的客户端)",
            HttpStatus.UNAUTHORIZED.value(),
            LocalDateTime.now());
        response.getWriter().print(objectMapper.writeValueAsString(result));
        response.flushBuffer();
    }
}
