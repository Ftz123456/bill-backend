package com.zsc.edu.bill.modules.system.controller;

import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.mapper.RoleMapper;
import com.zsc.edu.bill.modules.system.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 角色Controller
 *
 * @author harry yao
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/rest/role")
public class RoleController {

    private final RoleMapper roleMapper;

    private final RoleService service;


    /**
     * 返回所有角色列表 hasAuthority('ROLE_QUERY')
     *
     * @return 所有角色列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    public Collection<Role> list() {
        return service.list();
    }

    /**
     * 新建角色 hasAuthority('ROLE_CREATE')
     *
     * @param dto 表单数据
     * @return Role 新建的角色
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public Boolean create(@RequestBody RoleDto dto) {
        Role role= service.create(dto);
        return role != null;
    }

    /**
     * 更新角色 hasAuthority('ROLE_UPDATE')
     *
     * @param dto 表单数据
     * @param id  ID
     * @return Role 更新后的角色
     */
    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public Boolean update(@RequestBody RoleDto dto, @PathVariable("id") Long id) {
        Role role = roleMapper.toEntity(dto);
        role.setId(id);
        return service.updateById(role);
    }

    /**
     * 切换角色"启动/禁用"状态 hasAuthority('ROLE_UPDATE')
     *
     * @param id ID
     * @return Role 更新后的角色
     */
    @PatchMapping("{id}/toggle")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public Boolean toggle(@PathVariable("id") Long id) {
        return service.toggle(id);
    }

    /**
     * 查询角色详情 hasAuthority('ROLE_QUERY')
     *
     * @param id ID
     * @return Role 角色详情
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    public Role detail(@PathVariable Long id) {
        return service.detail(id);
    }

    /**
     * 删除角色 hasAuthority('ROLE_DELETE')
     *
     * @param id ID
     * @return Role 更新后的角色
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public Boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
