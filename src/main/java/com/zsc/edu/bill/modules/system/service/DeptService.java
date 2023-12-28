package com.zsc.edu.bill.modules.system.service;

import com.zsc.edu.bill.modules.system.dto.DeptDto;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
public interface DeptService extends IService<Dept> {

    /**
     * 创建部门
     * @param dto
     * @return
     */
    Dept create(DeptDto dto);

    /**
     * 更新部门
     * @param dto
     * @param id
     * @return
     */
    Boolean edit(DeptDto dto, Long id);

    /**
     * 生成部门树结构
     * @param id
     * @return
     */
//    Dept listTree(Long id);
}
