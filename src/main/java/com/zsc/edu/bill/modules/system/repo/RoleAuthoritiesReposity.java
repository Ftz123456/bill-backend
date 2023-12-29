package com.zsc.edu.bill.modules.system.repo;

import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

public interface RoleAuthoritiesReposity extends BaseMapper<RoleAuthority> {
    List<RoleAuthority> selectByRoleId(Long roleId);
}