package com.zsc.edu.bill.modules.system.service;


import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.system.dto.*;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.query.UserQuery;
import com.zsc.edu.bill.modules.system.vo.UserVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
public interface UserService extends IService<User> {

    User selfDetail(UserDetailsImpl userDetails);

    Boolean selfUpdate(UserDetailsImpl userDetails, UserSelfUpdateDto dto);

    Boolean selfUpdatePassword(UserDetailsImpl userDetails, UserSelfUpdatePasswordDto dto);

    Boolean create(UserCreateDto dto);

    Boolean update(UserUpdateDto dto, Long id);

    Boolean updatePassword(String password, Long id);

    Boolean toggle(Long id);

    Page<UserVo> page(UserQuery query, PageDTO pageDTO);

    Boolean register(UserCreateDto dto);

    Page<UserVo> page2(UserQuery query, PageDTO<User> page);

    PageDto<UserVo> queryUserPage(UserQuery query);
}
