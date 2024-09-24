package com.zsc.edu.bill.modules.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * 用户更新自己密码Dto
 *
 * @author harry yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSelfUpdatePasswordDto {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    public String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    public String password;

}
