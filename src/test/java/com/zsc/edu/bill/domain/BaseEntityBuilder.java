package com.zsc.edu.bill.domain;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * @author pengzheng
 */
public class BaseEntityBuilder {
    public Long id = -1L;
    public String remark;
    public LocalDateTime createAt;
    public LocalDateTime updateAt;

    public BaseEntityBuilder() {
        remark = randomAlphabetic(5);
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

}
