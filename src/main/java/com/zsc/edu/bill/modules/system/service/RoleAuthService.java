package com.zsc.edu.bill.modules.system.service;

import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
public interface RoleAuthService extends IService<RoleAuthority> {
    boolean removeByRoleId(Long id);
}
