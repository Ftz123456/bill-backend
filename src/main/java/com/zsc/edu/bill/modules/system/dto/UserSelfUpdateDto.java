package com.zsc.edu.bill.modules.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

/**
 * 用户更新自己信息Dto
 *
 * @author harry yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSelfUpdateDto {

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


}
