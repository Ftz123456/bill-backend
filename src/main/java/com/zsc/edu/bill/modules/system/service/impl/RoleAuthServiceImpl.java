package com.zsc.edu.bill.modules.system.service.impl;

import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.zsc.edu.bill.modules.system.repo.RoleAuthoritiesReposity;
import com.zsc.edu.bill.modules.system.service.RoleAuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthoritiesReposity, RoleAuthority> implements RoleAuthService {
    @Override
    public boolean removeByRoleId(Long roleId) {
        return remove(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, roleId));
    }
}
