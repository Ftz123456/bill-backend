package com.zsc.edu.bill.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Set;

/**
 * 用户
 *
 * @author harry yao
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties({"password"})
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    public String username;

    /**
     * 密码
     */
    public String password;

    /**
     * 手机号码
     */
    public String phone;

    /**
     * 电子邮件
     */
    public String email;

    /**
     * 启用状态
     */
    public Boolean enabled = true;


    /**
     * 所属部门ID
     */
    public Long deptId;

    /**
     * 角色ID
     */
    public Long roleId;

    /**
     * 所属部门
     */
    @TableField(exist = false)
    public Dept dept;


    /**
     * 拥有的角色
     */
    @TableField(exist = false)
    public Role role;


}

