package com.zsc.edu.bill.framework.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author harry_yao
 */
@AllArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//    private final OnlineUserService onlineUserService;
//    private final UserService userService;
//    private final LoginLogService loginLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String sessionId = request.getRequestedSessionId();
//        String remoteAddr = request.getRemoteAddr();
//        User user = userService.getOne(((UserDetailsImpl) principal).getId());
//        String agent = request.getHeader("User-Agent");
//        UserAgent userAgent = new UserAgent(agent);
//        OnlineUser onlineUser = onlineUserService.create(
//            sessionId,
//            user.username,
//            user.name,
//            null,//user.identity.dept.name,
//            remoteAddr,
//            userAgent.getBrowser().getName(),
//            userAgent.getOperatingSystem().getName(),
//            LocalDateTime.now()
//        );
//        loginLogService.create(onlineUser.username, onlineUser.name, onlineUser.deptName,
//            onlineUser.ip, onlineUser.browser, onlineUser.os,
//            onlineUser.loginTime, LoginLog.Result.登陆成功);
    }

}
