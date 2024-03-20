package com.zsc.edu.bill.modules.bills.entity;

import lombok.Data;

/**
 * @author ftz
 * 创建时间:2024/3/19 19:48
 * 描述: 首页返回数据
 */
@Data
public class Home {
    private Long notFiled =0L;
    private Long notAudit =0L;
    private Long pass =0L;
    private Long notPass =0L;


}
