package com.zsc.edu.bill.modules.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ftz
 * 创建时间:12/1/2024 下午1:55
 * 描述: 分页查询
 */
@Data
public class PageDto<T> {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 集合
     */
    private List<T> list;
}
