package com.zsc.edu.bill.modules.system.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsc.edu.bill.modules.system.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * @author ftz
 * 创建时间:11/1/2024 上午11:04
 * 描述: 前端传递的查询参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketQuery extends PageQuery {
    /**
     * 票据uuid 提供给前端显示用
     */
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
public LambdaQueryWrapper<Ticket> wrapper() {
        LambdaQueryWrapper<Ticket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(this.uuid), Ticket::getUuid, this.uuid);
        queryWrapper.eq(StringUtils.hasText(String.valueOf(this.userId)), Ticket::getUserId, this.userId);
        queryWrapper.eq(StringUtils.hasText(this.title), Ticket::getTitle, this.title);
        queryWrapper.eq(StringUtils.hasText(this.body), Ticket::getBody, this.body);
        queryWrapper.eq(StringUtils.hasText(String.valueOf(this.money)), Ticket::getMoney, this.money);
        queryWrapper.eq(StringUtils.hasText(String.valueOf(this.status)), Ticket::getStatus, this.status);
        queryWrapper.eq(StringUtils.hasText(String.valueOf(this.ticketType)), Ticket::getTicketType, this.ticketType);
        queryWrapper.eq(StringUtils.hasText(this.contactEmail), Ticket::getContactEmail, this.contactEmail);
        queryWrapper.eq(StringUtils.hasText(this.companyName), Ticket::getCompanyName, this.companyName);
        return queryWrapper;
    }
}
