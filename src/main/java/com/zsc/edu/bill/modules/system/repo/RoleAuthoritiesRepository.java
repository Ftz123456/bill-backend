package com.zsc.edu.bill.modules.system.repo;

import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Yao
 */
public interface RoleAuthoritiesRepository extends BaseMapper<RoleAuthority> {

    @Select("select * from sys_role_authorities where role_id=#{roleId}")
    List<RoleAuthority> selectByRoleId(Long roleId);
}