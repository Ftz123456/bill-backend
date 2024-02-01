package com.zsc.edu.bill.framework.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author harry_yao
 */
@ConfigurationProperties("storage")
@Component
public class StorageProperties {

    /**
     * 附件存储路径
     */
    @Value("${storage.attachment}")
    public String attachment;

    /**
     * 临时文件存储路径
     */
    @Value("${storage.temp}")
    public String temp;
}
