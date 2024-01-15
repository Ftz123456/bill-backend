package com.zsc.edu.bill.modules.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.util.Set;

/**
 * 用户新建Dto
 *
 * @author harry yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    public String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    public String password;

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
     * 用户身份集合
     */
    //@NotEmpty(message = "角色不能为空")
    public Set<Long> roleIds;

    /**
     * 备注说明
     */
    public String remark;

}
