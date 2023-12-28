package com.zsc.edu.bill;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.*;
import com.zsc.edu.bill.modules.system.repo.DeptRepository;
import com.zsc.edu.bill.modules.system.repo.RoleRepository;
import com.zsc.edu.bill.modules.system.repo.UserRepository;
import com.zsc.edu.bill.modules.system.repo.UserRolesReposity;
import com.zsc.edu.bill.modules.system.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Profile("!test")
public class FirstTimeInitializer implements CommandLineRunner {

    private final DeptRepository deptRepo;
    private final RoleRepository roleRepo;
    private final RoleService roleService;
    private final UserRolesReposity userRolesRepo;
    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        Dept dept1 = new Dept();
        Role role = new Role();
        if (deptRepo.selectCount(new QueryWrapper<>()) == 0) {
            dept1.setName("管理部门");
            deptRepo.insert(dept1);
        }
        if (roleRepo.selectCount(new QueryWrapper<>()) == 0) {
            RoleDto dto = new RoleDto();
            dto.setName("超级管理员");
            dto.setAuthorities(new HashSet<>(Arrays.asList(Authority.values())));
            role = roleService.create(dto);
        }
        if (userRepo.selectCount(new QueryWrapper<>()) == 0) {
            User user = new User();
            user.setUsername("管理员");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setEnabled(true);
            user.setPhone("13827993921");
            user.setEmail("123@qq.com");
            user.setDeptId(dept1.getId());
            user.setRoleId(role.getId());
            userRepo.insert(user);
        }
    }
}
