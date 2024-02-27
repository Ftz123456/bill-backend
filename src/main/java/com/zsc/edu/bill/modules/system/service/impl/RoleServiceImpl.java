package com.zsc.edu.bill.modules.system.service.impl;

import com.zsc.edu.bill.exception.ConstraintException;
import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.zsc.edu.bill.modules.system.entity.UserRole;
import com.zsc.edu.bill.modules.system.mapper.RoleMapper;
import com.zsc.edu.bill.modules.system.repo.RoleRepository;
import com.zsc.edu.bill.modules.system.repo.UserRolesReposity;
import com.zsc.edu.bill.modules.system.service.RoleAuthService;
import com.zsc.edu.bill.modules.system.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
@AllArgsConstructor
@Service
public class RoleServiceImpl extends ServiceImpl<RoleRepository, Role> implements RoleService {

    private final RoleMapper mapper;
    private final UserRolesReposity urRepo;

    private final RoleAuthService roleAuthService;

    @Override
    public Role create(RoleDto dto) {
        boolean existsByName = count(new LambdaQueryWrapper<Role>().eq(Role::getName, dto.getName())) > 0;
        if (existsByName) {
            throw new ConstraintException("name", dto.name, "角色已存在");
        }
        Role role = mapper.toEntity(dto);
        save(role);
        saveRoleAuths(role.getId(), role.getAuthorities());
        return role;
    }

    @Override
    public Boolean toggle(Long id) {
        Role role = getById(id);
        role.setEnabled(!role.getEnabled());
        return updateById(role);
    }

    @Override
    public Boolean delete(Long id) {
        boolean hasUser = urRepo.selectCount(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId, id)) > 0;
        if (hasUser) {
            throw new ConstraintException("存在与本角色绑定的用户，请先删除用户");
        }

        // 删除角色权限关联关系
        roleAuthService.removeByRoleId(id);
        return removeById(id);
    }

    @Override
    public Boolean updateRole(RoleDto dto, Long id) {
        Role role =mapper.toEntity(dto);
        role.setId(id);
        if (dto.getAuthorities() != null && !dto.getAuthorities().isEmpty()) {
            roleAuthService.remove(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, id));
            Set<Authority> authorities = new HashSet<>(dto.getAuthorities());
            roleAuthService.saveBatch(authorities.stream().map(authority -> new RoleAuthority(id, authority.getAuthority())).collect(Collectors.toList()));

        }
           return updateById(role);

    }

    @Override
    public Boolean edit(RoleDto dto, Long id) {
        boolean existsByName = count(new LambdaQueryWrapper<Role>().ne(Role::getId, id).eq(Role::getName, dto.getName())) > 0;
        if (existsByName) {
            throw new ConstraintException("name", dto.name, "同名角色已存在");
        }
        roleAuthService.remove(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, id));
        saveRoleAuths(id, dto.getAuthorities());
        return update(dto.updateWrapper(id));
    }

    @Override
    public Role detail(Long id) {
        Role role = getById(id);
        List<RoleAuthority> roleAuthorities = roleAuthService.list(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, role.id));
        role.authorities = roleAuthorities.stream()
                .map(roleAuthority -> Authority.valueOf(roleAuthority.getAuthority()))
                .collect(Collectors.toSet());
        return role;
    }

    private boolean saveRoleAuths(Long roleId, Set<Authority> authorities) {
        // 保存角色关联权限
        List<RoleAuthority> roleAuthorities = authorities.stream().map(authority -> new RoleAuthority(roleId, authority.getAuthority())).collect(Collectors.toList());
        return roleAuthService.saveBatch(roleAuthorities);
    }
}
