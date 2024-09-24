package com.zsc.edu.bill.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.exception.ConstraintException;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.system.dto.*;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.query.UserQuery;
import com.zsc.edu.bill.modules.system.repo.UserRepository;
import com.zsc.edu.bill.modules.system.service.UserService;
import com.zsc.edu.bill.modules.system.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yao
 * @since 2023-04-06
 */
@AllArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleServiceImpl roleService;


    @Override
    public Boolean create(UserCreateDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return save(user);
    }

    @Override
    public Boolean update(UserUpdateDto dto, Long id) {
        User user = getById(id);
        boolean existsByPhone = count(new LambdaQueryWrapper<User>().eq(User::getPhone, dto.getPhone())) > 0;
        boolean existsByEmail = count(new LambdaQueryWrapper<User>().eq(User::getEmail, dto.getEmail())) > 0;
        if (user.getPhone().equals(dto.getPhone()) && existsByPhone) {
            throw new ConstraintException("phone", dto.phone, "手机号已存在");
        }
        if (user.getEmail().equals(dto.getEmail()) && existsByEmail) {
            throw new ConstraintException("email", dto.email, "邮箱地址已存在");
        }
        BeanUtils.copyProperties(dto, user);
        return updateById(user);
    }

    @Override
    public Boolean updatePassword(String password, Long id) {
        User user = getById(id);
        user.setPassword(passwordEncoder.encode(password));
        return save(user);
    }

    @Override
    public Boolean toggle(Long id) {
        User user = getById(id);
        user.setEnabled(!user.getEnabled());
        return updateById(user);
    }

    @Override
    public Page<UserVo> page(UserQuery query, PageDTO pageDTO) {
        //2.构造连表查询的动态SQL代码⽚段
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.like(!query.getUsername().isBlank(), "u.username", query.getUsername())
//        wrapper
//                .in(query.deptIds != null && !query.deptIds.isEmpty(), "d.id", query.getDeptIds())
//                .in(query.roleId != null && !query.roleId.isEmpty(), "r.id", query.getRoleId());
        //3.调⽤Mapper层连表查询SQL语句，并把动态查询的代码⽚段传递给Mapper接⼝
        return this.baseMapper.page(pageDTO, wrapper);
    }

    @Override
    public Boolean register(UserCreateDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        if (count(new LambdaQueryWrapper<User>().eq(User::getPhone, dto.getPhone())) > 0) {
            throw new ConstraintException("phone", dto.phone, "手机号已存在");
        }
        if (count(new LambdaQueryWrapper<User>().eq(User::getEmail, dto.getEmail())) > 0) {
            throw new ConstraintException("email", dto.email, "邮箱地址已存在");
        }
        user.setPassword(passwordEncoder.encode(dto.password));
        user.setRoleId(roleService.getOne(new LambdaQueryWrapper<Role>().eq(Role::getName, "普通用户")).getId());
        return save(user);
    }

    @Override
    public Page<UserVo> page2(UserQuery query, PageDTO<User> page) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return this.baseMapper.page(page, wrapper);
    }

    @Override
    public PageDto<UserVo>  queryUserPage(UserQuery query) {
        String username = query.getUsername();
        String phone = query.getPhone();
        String email = query.getEmail();
        Page<User> page = Page.of(query.getPage(), query.getSize());
        if (query.getOrderBy()!=null) {
            page.addOrder(new OrderItem().setColumn(query.getOrderBy()).setAsc(query.getAsc()));
        } else {
         page.addOrder(new OrderItem().setColumn("id").setAsc(false));
        }
        Page<User> p = lambdaQuery()
                .like(username != null && !username.isBlank(), User::getUsername, username)
                .like(phone != null && !phone.isBlank(), User::getPhone, phone)
                .like(email != null && !email.isBlank(), User::getEmail, email)
                .page(page);
        PageDto<UserVo> dto = new PageDto<>();
        dto.setTotal(p.getTotal());
        dto.setPages((int) p.getPages());
        List<UserVo> list = p.getRecords().stream().map(user -> {
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(user, vo);
            return vo;
        }).toList();
        dto.setList(list);

        return dto;
    }

    @Override
    public User selfDetail(UserDetailsImpl userDetails) {
        return getById(userDetails.getId());
    }

    @Override
    public Boolean selfUpdate(UserDetailsImpl userDetails, UserSelfUpdateDto dto) {
        User user = getById(userDetails.getId());
        boolean existsByPhone = count(new LambdaQueryWrapper<User>().ne(User::getId, userDetails.getId()).eq(User::getPhone, dto.getPhone())) > 0;
        boolean existsByEmail = count(new LambdaQueryWrapper<User>().ne(User::getId, userDetails.getId()).eq(User::getEmail, dto.getEmail())) > 0;
        if (existsByPhone) {
            throw new ConstraintException("phone", dto.phone, "手机号已存在");
        }
        if (existsByEmail) {
            throw new ConstraintException("email", dto.email, "邮箱地址已存在");
        }
        BeanUtils.copyProperties(dto, user);
        return updateById(user);
    }

    @Override
    public Boolean selfUpdatePassword(UserDetailsImpl userDetails, UserSelfUpdatePasswordDto dto) {
        User user = getById(userDetails.getId());
        if (!passwordEncoder.matches(dto.oldPassword, user.password)) {
            throw new ConstraintException("旧密码不对");
        }
        user.setPassword(passwordEncoder.encode(dto.password));
        return updateById(user);
    }

}
