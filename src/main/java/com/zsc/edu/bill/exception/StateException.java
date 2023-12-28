package com.zsc.edu.bill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harry_yao
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StateException extends ApiException {

    public StateException(Class<?> statusClass, Object currentStatus, Object correctStatus) {
        super(String.format("%s当前的状态值'%s'不符合要求，正确的状态值可以是：%s。", statusClass.getSimpleName(), currentStatus, correctStatus));
    }

    public StateException(String message) {
        super(message);
    }
}
