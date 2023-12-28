package com.zsc.edu.bill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harry_yao
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserHasNoIdentityException extends DisabledException {

    public UserHasNoIdentityException() {
        super("没有给用户分配身份");
    }

    public UserHasNoIdentityException(String message) {
        super(message);
    }

}
