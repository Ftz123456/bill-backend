package com.zsc.edu.bill.modules.system.service;


import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
public interface RoleService extends IService<Role> {

    Role create(RoleDto roleDto);

    Boolean edit(RoleDto roleDto, Long id);

    Role detail(Long id);

    Boolean toggle(Long id);

    Boolean delete(Long id);

    Boolean updateRole(RoleDto dto, Long id);
}
