package com.zsc.edu.bill.modules.system.query;

import lombok.Data;

/**
 * @author ftz
 * 创建时间:12/1/2024 下午1:49
 * 描述: 分页查询
 */
@Data
public class PageQuery {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页大小
     */
    private Integer size;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * 是否升序
     */
    private Boolean asc;
}
