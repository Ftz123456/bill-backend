package com.zsc.edu.bill.modules.system.controller;

import com.zsc.edu.bill.modules.system.dto.DeptDto;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.query.DeptQuery;
import com.zsc.edu.bill.modules.system.service.DeptService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 部门Controller
 *
 * @author pengzheng
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/rest/dept")
public class DeptController {

    private final DeptService service;

    /**
     * 返回管理部门列表 hasAuthority('DEPT_QUERY')
     *
     * @param query 查询表单
     * @return 部门列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('DEPT_QUERY')")
    public Collection<Dept> list(DeptQuery query) {
        return service.list(query.wrapper());
    }

    /**
     * 新建管理部门 hasAuthority('DEPT_CREATE')
     *
     * @param dto 表单数据
     * @return Dept 新建的管理部门
     */
    @PostMapping
    @PreAuthorize("hasAuthority('DEPT_CREATE')")
    public Dept create(@RequestBody DeptDto dto) {
        return service.create(dto);
    }

    /**
     * 更新管理部门 hasAuthority('DEPT_UPDATE')
     *
     * @param dto 表单数据
     * @param id  部门ID
     * @return Dept 更新后的部门
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('DEPT_UPDATE')")
    public Boolean update(@RequestBody DeptDto dto, @PathVariable("id") Long id) {
        return service.edit(dto, id);
    }

}
