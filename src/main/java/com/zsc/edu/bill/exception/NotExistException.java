package com.zsc.edu.bill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harry_yao
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotExistException extends ApiException {

    public NotExistException(Class<?> entity) {
        super(String.format("%s对象不存在", entity.getSimpleName()));
    }

    public NotExistException() {
        super("对象不存在");
    }

    public NotExistException(String message) {
        super(message);
    }
}
