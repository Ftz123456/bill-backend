package com.zsc.edu.bill.modules.system.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 用户Query
 *
 * @author harry yao
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {

    /**
     * 用户名
     */
    public String username;

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
    public Boolean enable;

    /**
     * 角色集合
     */
    public Long roleId;

    /**
     * 部门集合
     */
    public Long deptId;


    public LambdaQueryWrapper<User> wrapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(this.username), User::getUsername, this.username);
        queryWrapper.eq(StringUtils.hasText(this.phone), User::getPhone, this.phone);
        queryWrapper.eq(StringUtils.hasText(this.email), User::getEmail, this.email);
        queryWrapper.eq(Objects.nonNull(this.enable), User::getEnabled, this.enable);
        queryWrapper.eq(Objects.nonNull(this.deptId),User::getDeptId,this.deptId);
        queryWrapper.eq(Objects.nonNull(this.roleId),User::getRoleId,this.roleId);
        return queryWrapper;
    }

}
