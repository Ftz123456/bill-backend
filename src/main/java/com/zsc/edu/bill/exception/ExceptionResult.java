package com.zsc.edu.bill.exception;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author harry_yao
 */
@AllArgsConstructor
public class ExceptionResult {

    public final String msg;
    public final Object code;
    public final LocalDateTime timestamp;

}
