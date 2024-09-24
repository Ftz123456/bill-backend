package com.zsc.edu.bill.common.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author ftz
 * 创建时间:11/1/2024 下午1:54
 * 描述: 审核员权限
 */
public enum Auditor implements GrantedAuthority {



    /**
     * 用户管理
     */
    USER_QUERY,
    USER_CREATE,
    /**
     * 审核票据

     */
    AUDIT_TICKET_QUERY,
    AUDIT_TICKET_UPDATE;

    @Override
    public String getAuthority() {
        return name();
    }
}
