package com.zsc.edu.bill.domain;

import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.Role;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * @author pengzheng
 */
public final class RoleBuilder extends BaseEntityBuilder {
    public String name;
    public boolean enable;
    public Set<Authority> authorities;

    private RoleBuilder() {
        this.name = randomAlphabetic(5);
        this.enable = true;
        this.authorities = new HashSet<>();
    }

    public static RoleBuilder aRole() {
        return new RoleBuilder();
    }

    public RoleBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RoleBuilder enable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public RoleBuilder authorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public Role build() {
        Role role = new Role();
        role.setRemark(remark);
        role.setCreateTime(createAt);
        role.setUpdateTime(updateAt);
        role.setName(name);
        role.setEnabled(enable);
        role.setAuthorities(authorities);
        return role;
    }
}
