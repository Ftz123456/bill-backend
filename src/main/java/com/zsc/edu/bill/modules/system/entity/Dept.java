package com.zsc.edu.bill.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门
 *
 * @author Yao
 * @since 2023-04-06
 */
@Getter
@Setter
@TableName("sys_dept")
public class Dept extends BaseEntity {

    /**
     * 上级部门
     */
    private Long pid;

    /**
     * 子部门数目
     */
    private Integer subCount;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 状态
     */
    private Boolean enabled = true;

    @TableField(exist = false)
    public Set<Dept> children = new HashSet<>(0);

}
