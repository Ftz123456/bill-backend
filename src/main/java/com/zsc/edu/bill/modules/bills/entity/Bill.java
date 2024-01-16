package com.zsc.edu.bill.modules.bills.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zsc.edu.bill.modules.system.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * 票据表
 * @author verto
 * @TableName bill
 */
@TableName(value ="bill")
@Getter
@Setter
public class Bill extends BaseEntity {

    /**
     * 票据uuid 提供给前端显示用
     */
    private UUID uuid;

    /**
     * 用户id 票据创建者id
     */
    private Long userId;

    /**
     * 票据标题
     */
    private String title;

    /**
     * 主体内容 票据详细内容
     */
    private String body;

    /**
     * 金额 票据金额
     */
    private BigDecimal money;

    /**
     * 状态 0:未提交，草稿;1:未审核；2:审核通过；3:退回、审核未通过
     */
    private Status status;

    /**
     * 票据类型
     */
    private String type;

    /**
     * 联系方式email邮箱
     */
    private String contactEmail;

    /**
     * 客户公司名称 票据对应的企业名称
     */
    private String companyName;

    @Getter
    public enum Status {
        SUBMIT(0, "待提交"),
        EXAMINE(1, "待审核"),
        PASS(2, "审核通过"),
        FAILED(3, "审核未通过");

        @EnumValue
        private final int code;

        @JsonValue
        private final String name;

        Status(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static Status get(String name) {
            for (Status item : values()) {
                if (item.getName() .equals(name)) {
                    return item;
                }
            }
            return null;
        }

    }

}