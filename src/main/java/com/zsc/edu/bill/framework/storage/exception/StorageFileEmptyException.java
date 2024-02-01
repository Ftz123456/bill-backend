package com.zsc.edu.bill.framework.storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harry_yao
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StorageFileEmptyException extends StorageException {

    public StorageFileEmptyException() {
        super("存储的是空文件！");
    }

    public StorageFileEmptyException(String message) {
        super(message);
    }

    public StorageFileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
