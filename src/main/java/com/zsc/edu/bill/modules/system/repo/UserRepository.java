package com.zsc.edu.bill.modules.system.repo;

import com.zsc.edu.bill.modules.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsc.edu.bill.modules.system.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
public interface UserRepository extends BaseMapper<User> {

    @Select("select u.*, d.name as deptName, GROUP_CONCAT(r.name SEPARATOR ', ') as roleNames from sys_user as u " +
            "left join sys_dept as d on u.dept_id=d.id " +
            "left join sys_users_roles as sur on u.id = sur.user_id " +
            "join sys_role r on r.id = sur.role_id ${ew.customSqlSegment}")
    Page<UserVo> page(Page pageDTO, @Param("ew")  QueryWrapper<User> wrapper);

    @Select("select u.* from sys_user u where u.username = ${username}")
    User findByUsername(String username);
}
