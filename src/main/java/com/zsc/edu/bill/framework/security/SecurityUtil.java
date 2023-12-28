package com.zsc.edu.bill.framework.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Yao
 */
public class SecurityUtil {

    public static UserDetailsImpl getUserInfo() {
        return getPrincipal();
    }

    private static UserDetailsImpl getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}
