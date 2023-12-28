package com.zsc.edu.bill.modules.system.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVo {
    String username;
    String phone;
    String email;
    Boolean enabled;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}
