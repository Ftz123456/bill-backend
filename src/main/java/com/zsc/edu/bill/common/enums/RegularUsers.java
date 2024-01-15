package com.zsc.edu.bill.common.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author ftz
 * 创建时间:11/1/2024 下午2:04
 * 描述: TODO
 */
public enum RegularUsers implements GrantedAuthority {
    /**
     * 用户管理
     */
    USER_QUERY,
    USER_CREATE,
    USER_UPDATE,
    /**
     * 票据管理
     */
    TICKET_QUERY,
    TICKET_CREATE,
    TICKET_UPDATE,
    TICKET_DELETE;

    @Override
    public String getAuthority() {
        return name();
    }
}
