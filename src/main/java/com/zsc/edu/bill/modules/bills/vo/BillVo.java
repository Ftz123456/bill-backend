package com.zsc.edu.bill.modules.bills.vo;

import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.system.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BillVo extends BaseEntity {
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
    private Bill.Status status;

    /**
     * 票据类型
     */
    private Bill.billType type;


    /**
     * 联系方式email邮箱
     */
    private String contactEmail;

    /**
     * 客户公司名称 票据对应的企业名称
     */
    private String companyName;
    /**
     *附件id
     */
    private String attachId;
    /*
     *审核员id
     * */
    private Long auditorId;
    /*
     *审核意见
     */
    private String comment;


}
