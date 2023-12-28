package com.zsc.edu.bill.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * sys_role_authorities
 * @author 
 */
@AllArgsConstructor
@Data
@TableName("sys_role_authorities")
public class RoleAuthority implements Serializable {
    private Long roleId;

    private String authority;

    private static final long serialVersionUID = 1L;
}