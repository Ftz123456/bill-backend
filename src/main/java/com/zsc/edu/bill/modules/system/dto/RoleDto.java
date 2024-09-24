package com.zsc.edu.bill.modules.system.dto;

import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

/**
 * 角色Dto
 *
 * @author harry yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    public String name;

    /**
     * 备注
     */
    public String remark;

    /**
     * 权限列表
     */
    public Set<Authority> authorities;

    public LambdaUpdateWrapper<Role> updateWrapper(Long id) {
        LambdaUpdateWrapper<Role> update = new LambdaUpdateWrapper<>();
        return update.eq(Role::getId, id)
                .set(Role::getName, name)
                .set(StringUtils.hasText(remark), Role::getRemark, remark);
    }

}
