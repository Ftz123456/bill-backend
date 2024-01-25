package com.zsc.edu.bill.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 角色
 *
 * @author harry yao
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class Role extends BaseEntity {

    /**
     * 名称，唯一
     */
    public String name;

    /**
     * 启用状态
     */
    private Boolean enabled = true;

    /**
     * 权限集合
     */
    @TableField(exist = false)
    public Set<Authority> authorities;


}

