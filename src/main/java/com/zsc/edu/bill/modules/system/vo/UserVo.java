package com.zsc.edu.bill.modules.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVo {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    public Long id;
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
    public Boolean enabled;
    /**
     *
     *昵称
     * */
    public String nickName;

    /**
     * 所属部门ID
     */
    public Long deptId;

    /**
     * 角色ID
     */
    public Long roleId;
    /**
     * 头像
     */
    public String avatar;
    /**
     * 地址
     */
    public String address;

    LocalDateTime createTime;
    LocalDateTime updateTime;

}
