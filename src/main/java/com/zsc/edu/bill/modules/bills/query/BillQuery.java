package com.zsc.edu.bill.modules.bills.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author ftz
 * 创建时间:11/1/2024 上午11:04
 * 描述: 前端传递的查询参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillQuery {
    /**
     * 用户名字
     * */
    private String userName;

    /**
     * 用户id 票据创建者id
     */
    private Long userId;

    /**
     * 票据标题
     */
    private String title;

    /**
     * 最大金额 票据金额
     */
    private BigDecimal maxMoney;
    /**
     * 最小金额 票据金额
     */
    private BigDecimal minMoney;
    /**
     * 状态 0:未提交，草稿;1:未审核；2:审核通过；3:退回、审核未通过
     */
    private Bill.Status status;

    /**
     * 票据类型
     */
    private Bill.billType type;

    /**
     * 客户公司名称 票据对应的企业名称
     */
    private String companyName;
    /**
     * 审核人id
     */
    private Long auditorId;
    /**
     * 起始时间
     * */
    @DateTimeFormat(pattern =  "yyyy-MM-dd")
    private LocalDate startTime;
    /**
     * 结束时间
     * */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    /**
     * 拼接查询条件
     */
    public LambdaQueryWrapper<Bill> wrapper() {
        LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(this.userId), Bill::getUserId, this.userId);
        queryWrapper.like(StringUtils.hasText(this.userName), Bill::getUserName, this.userName);
        queryWrapper.like(StringUtils.hasText(this.title), Bill::getTitle, this.title);
        queryWrapper.between(Objects.nonNull(this.minMoney) && Objects.nonNull(this.maxMoney), Bill::getMoney, this.minMoney, this.maxMoney);
        queryWrapper.eq(Objects.nonNull(this.status), Bill::getStatus, this.status);
        queryWrapper.eq(Objects.nonNull(this.type), Bill::getType, this.type);
        queryWrapper.eq(Objects.nonNull(this.auditorId), Bill::getAuditorId, this.auditorId);
        queryWrapper.like(StringUtils.hasText(this.companyName), Bill::getCompanyName, this.companyName);
        queryWrapper.between(Objects.nonNull(this.startTime) && Objects.nonNull(this.endTime), Bill::getCreateTime, this.startTime, this.endTime);
        queryWrapper.orderByAsc(Bill::getStatus);
        return queryWrapper;
    }
}
