package com.zsc.edu.bill.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsc.edu.bill.exception.ConstraintException;
import com.zsc.edu.bill.modules.system.dto.DeptDto;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.query.DeptQuery;
import com.zsc.edu.bill.modules.system.service.DeptService;
import com.zsc.edu.bill.modules.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private final UserService userService;

    /**
     * 返回管理部门列表 hasAuthority('DEPT_QUERY')
     *
     * @param query 查询表单
     * @return 部门列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('DEPT_QUERY')")
    public Page<Dept> query(DeptQuery query, Page<Dept> page) {
        return service.page(page, query.wrapper());
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
    /***
     *  删除管理部门 hasAuthority('DEPT_DELETE')
     *  @param id 部门ID
     *  @return Boolean 是否删除成功
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DEPT_DELETE')")
    public Boolean delete(@PathVariable("id") Long id) {
        /**
         * 是否存在用户绑定此部门
         * */
        boolean hasUser = userService.count(new LambdaQueryWrapper<User>().eq(User::getDeptId, id)) > 0;
        if (hasUser) {
            throw new ConstraintException("存在与本部门绑定的用户，请先删除用户");
        }
        return service.removeById(id);
    }
    /**
     * 更新管理部门状态
     * */
    @PatchMapping("/toggle/{id}")
    @PreAuthorize("hasAuthority('DEPT_TOGGLE')")
    public Boolean toggle(@PathVariable("id") Long id) {
        return service.toggle(id);
    }


}
