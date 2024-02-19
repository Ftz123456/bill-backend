package com.zsc.edu.bill.modules.system.service.impl;

import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.zsc.edu.bill.modules.system.repo.RoleAuthoritiesRepository;
import com.zsc.edu.bill.modules.system.service.RoleAuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthoritiesRepository, RoleAuthority> implements RoleAuthService {
    @Override
    public boolean removeByRoleId(Long roleId) {
        return remove(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, roleId));
    }

    @Override
    public List<String> getAuthorityByUserId(Long id) {
        List<RoleAuthority> list = list(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, id));
        return list(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, id)).stream().map(RoleAuthority::getAuthority).collect(Collectors.toList());


    }
}
