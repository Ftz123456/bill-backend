package com.zsc.edu.bill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harry_yao
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConstraintException extends ApiException {

    public ConstraintException(String fieldName, Object fieldValue) {
        super(String.format("字段%s的值：'%s'不符合要求。", fieldName, fieldValue));
    }

    public ConstraintException(String fieldName, Object fieldValue, String message) {
        super(String.format("字段%s的值：'%s'不符合要求，%s", fieldName, fieldValue, message));
    }

    public ConstraintException(String message) {
        super(message);
    }
}
