package com.zsc.edu.bill.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Yao
 */
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
