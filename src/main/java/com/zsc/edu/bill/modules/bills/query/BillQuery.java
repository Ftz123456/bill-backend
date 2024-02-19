package com.zsc.edu.bill.modules.bills.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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
     * 客户公司名称 票据对应的企业名称
     */
    private String companyName;

    /**
     * 拼接查询条件
     * @return
     */
    public LambdaQueryWrapper<Bill> wrapper() {
        LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(this.uuid), Bill::getUuid, this.uuid);
        queryWrapper.eq(Objects.nonNull(this.userId), Bill::getUserId, this.userId);
        queryWrapper.like(StringUtils.hasText(this.title), Bill::getTitle, this.title);
        queryWrapper.eq(Objects.nonNull(this.money), Bill::getMoney, this.money);
        queryWrapper.eq(Objects.nonNull(this.status), Bill::getStatus, this.status);
        queryWrapper.eq(Objects.nonNull(this.type), Bill::getType, this.type);
        queryWrapper.like(StringUtils.hasText(this.companyName), Bill::getCompanyName, this.companyName);
        return queryWrapper;
    }
}
