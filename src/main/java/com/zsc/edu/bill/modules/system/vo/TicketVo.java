package com.zsc.edu.bill.modules.system.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ftz
 * 创建时间:13/1/2024 下午3:00
 * 描述: 票据vo
 */
@Data
public class TicketVo {
    private String uuid;

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
    private Integer status;

    /**
     * 票据类型
     */
    private Integer ticketType;

    /**
     * 联系方式email邮箱
     */
    private String contactEmail;

    /**
     * 客户公司名称 票据对应的企业名称
     */
    private String companyName;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
