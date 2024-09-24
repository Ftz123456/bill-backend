package com.zsc.edu.bill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harry_yao
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutlineException extends ApiException {

    public OutlineException() {
        super("设备不在线");
    }

    public OutlineException(String message) {
        super(message);
    }
}
