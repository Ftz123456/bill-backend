package com.zsc.edu.bill.modules.system.dto;

import com.zsc.edu.bill.modules.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Set;

/**
 * 用户更新Dto
 *
 * @author harry yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    /**
     * 手机号
     */
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "手机号格式不对")
    public String phone;

    /**
     * 邮箱
     */
    @Email(message = "电子邮箱格式不对")
    public String email;

    /**
     * 启用状态
     */
    @NotNull(message = "启用状态不能为空")
    public Boolean enable;

    /**
     * 部门ID
     */
    @NotNull(message = "部门不能为空")
    public Long deptId;
    /**
     * 昵称
     * */
    public String nickName;
    /**
     * 头像
     * */
    public String avatar;
    /**
     * 地址
     * */
    public String address;

    /**
     * 用户身份集合
     */
    @NotEmpty(message = "角色不能为空")
    public Set<Long> roleIds;

    public String remark;

    public LambdaUpdateWrapper<User> updateWrapper(Long id) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        return updateWrapper.eq(User::getId, id)
                .set(User::getPhone, phone)
                .set(StringUtils.hasText(email), User::getEmail, email)
                .set(User::getEnabled, enable)
                .set(User::getDeptId, deptId)
                .set(User::getPhone, phone)
                .set(StringUtils.hasText(remark), User::getRemark, remark);
    }

}
