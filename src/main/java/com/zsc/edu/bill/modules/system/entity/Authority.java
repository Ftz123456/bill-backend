package com.zsc.edu.bill.modules.system.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * 操作权限
 *
 * @author harry yao
 */
public enum Authority implements GrantedAuthority {

    /**
     * 部门管理
     */
    DEPT_QUERY,
    DEPT_CREATE,
    DEPT_UPDATE,
    DEPT_DELETE,

    /**
     * 角色管理
     */
    ROLE_CREATE,
    ROLE_QUERY,
    ROLE_UPDATE,

    /**
     * 用户管理
     */
    USER_QUERY,
    USER_CREATE,
    USER_UPDATE,
    USER_DELETE;

    @Override
    public String getAuthority() {
        return name();
    }
}
