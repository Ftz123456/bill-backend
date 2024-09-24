package com.zsc.edu.bill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harry_yao
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StorageException extends ApiException {

    public StorageException() {
        super("文件存储失败");
    }

    public StorageException(String message) {
        super(message);
    }
}
