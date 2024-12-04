package com.zsc.edu.bill.modules.system.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsc.edu.bill.modules.system.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ftz
 * 创建时间:2024/4/8 8:06
 * 描述: 角色查询参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQuery {
    /**
     * 名称，唯一
     */
    public String name;

    /**
     * 启用状态
     */
    private Boolean enabled ;
    public LambdaQueryWrapper<Role> wrapper() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(this.enabled != null, Role::getEnabled, this.enabled);
        queryWrapper.like(this.name != null, Role::getName, this.name);
        return queryWrapper;
    }

}
