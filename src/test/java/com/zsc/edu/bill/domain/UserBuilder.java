package com.zsc.edu.bill.domain;

import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.entity.User;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @author pengzheng
 */
public final class UserBuilder extends BaseEntityBuilder {
    public String username;
    public String password;
    public String phone;
    public String email;
    public boolean enable;
    public Dept dept;
    public Role role;

    private UserBuilder() {
        this.username = randomAlphabetic(5);
        this.password = randomAlphabetic(5);
        this.phone = "139" + randomAlphanumeric(8);
        this.email = randomAlphanumeric(8) + "@" + randomAlphanumeric(6) + ".com";
        this.enable = true;
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder enable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public UserBuilder dept(Dept dept) {
        this.dept = dept;
        return this;
    }

    public UserBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public User build() {
        User user = new User();
        user.setRemark(remark);
        user.setCreateTime(createAt);
        user.setUpdateTime(updateAt);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setEnabled(enable);
        user.setDept(dept);
        user.setRole(role);
        return user;
    }
}
