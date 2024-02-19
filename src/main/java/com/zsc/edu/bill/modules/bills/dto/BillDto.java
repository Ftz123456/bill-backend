package com.zsc.edu.bill.modules.bills.dto;

import com.zsc.edu.bill.modules.bills.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    /**
     *userID
     * */
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
    private String status;

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
    /**
     * 附件id
     */
    private String attachId;
    /**
     * 审核人id
     */
    private Long auditorId;
    /**
     * 提交状态 true/false
     */
    private Boolean submit;

}
