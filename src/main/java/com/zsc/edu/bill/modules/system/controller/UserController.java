package com.zsc.edu.bill.modules.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.system.dto.*;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.query.UserQuery;
import com.zsc.edu.bill.modules.system.service.DeptService;
import com.zsc.edu.bill.modules.system.service.RoleService;
import com.zsc.edu.bill.modules.system.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.zsc.edu.bill.modules.system.vo.UserVo;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户Controller
 *
 * @author harry yao
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/rest/user")
public class UserController {

    private final UserService service;

    private final RoleService roleService;

    private final DeptService deptService;

    /**
     * 登录前，获取csrfToken信息
     * 登录成功后，获取principal和csrfToken信息
     *
     * @param principal 认证主体
     * @param csrfToken csrf令牌
     * @return 包含csrf令牌和登录用户的认证主体信息
     */
    @GetMapping("me")
    public Map<String, Object> me(@AuthenticationPrincipal Object principal, CsrfToken csrfToken) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", principal);
        map.put("csrf", csrfToken);
        return map;
    }

    /**
     * 用户获取自己的信息
     *
     * @param userDetails 操作用户
     * @return 用户信息
     */
    @GetMapping("self")
    public User selfDetail(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = service.selfDetail(userDetails);
        user.dept = deptService.getById(user.deptId);
        return user;
    }

    /**
     * 用户更新自己的信息
     *
     * @param userDetails 操作用户
     * @param dto         表单数据
     * @return 更新后的用户信息
     */
    @PatchMapping("self")
    public Boolean selfUpdate(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserSelfUpdateDto dto) {
        return service.selfUpdate(userDetails, dto);
    }

    /**
     * 用户更新自己的密码
     *
     * @param userDetails 操作用户
     * @param dto         表单数据
     */
    @PatchMapping("self/update-password")
    public Boolean selfUpdatePassword(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UserSelfUpdatePasswordDto dto
    ) {
        return service.selfUpdatePassword(userDetails, dto);
    }

    /**
     * 分页查询用户信息 hasAuthority('USER_QUERY')
     *
     * @param query    查询表单
     * @param page 分页
     * @return 分页用户信息
     */
    @GetMapping
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Page<UserVo> query(UserQuery query, PageDTO<User> page) {
        return service.page(query, page);
    }
    /**
     * 分页查询用户信息2 hasAuthority('USER_QUERY')
     *
     * @param query    查询表单
     * @return 分页用户信息
     */

    @GetMapping("query")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public PageDto<UserVo> query2(UserQuery query) {
        return service.queryUserPage(query);
    }

    /**
     * 新建用户 hasAuthority('USER_CREATE')
     *
     * @param dto 表单数据
     * @return 新建的用户信息
     */
    @PostMapping
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public Boolean create(@RequestBody UserCreateDto dto) {
        return service.create(dto);
    }
    /**
     * 注册用户
     * @param dto 表单数据
     * @return Boolean
     */
    @PostMapping("register")
    public Boolean register(@RequestBody UserCreateDto dto) {
        return service.register(dto);
    }

    /**
     * 更新用户 hasAuthority('USER_UPDATE')
     *
     * @param dto 表单数据
     * @param id  ID
     * @return 更新后的用户
     */
    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public Boolean update(@RequestBody UserUpdateDto dto, @PathVariable("id") Long id) {
        return service.update(dto, id);
    }

    /**
     * 更新用户密码 hasAuthority('USER_UPDATE')
     *
     * @param id       ID
     * @param password 新密码
     */
    @PatchMapping("{id}/update-password")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public void updatePassword(@PathVariable("id") Long id, @RequestParam String password) {
        service.updatePassword(password, id);
    }

    /**
     * 切换用户"启动/禁用"状态 hasAuthority('USER_DELETE')
     *
     * @param id ID
     * @return Dept 更新后的用户
     */
    @PatchMapping("{id}/toggle")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public Boolean toggle(@PathVariable("id") Long id) {
        return service.toggle(id);
    }

}
