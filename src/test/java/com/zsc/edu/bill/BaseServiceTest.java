package com.zsc.edu.bill;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.edu.bill.domain.DeptBuilder;
import com.zsc.edu.bill.domain.RoleBuilder;
import com.zsc.edu.bill.domain.UserBuilder;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.repo.DeptRepository;
import com.zsc.edu.bill.modules.system.repo.RoleRepository;
import com.zsc.edu.bill.modules.system.repo.UserRepository;
import com.zsc.edu.bill.modules.system.service.RoleService;
import com.zsc.edu.bill.modules.system.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * @author pengzheng
 */
//@ActiveProfiles("test")
@SpringBootTest
abstract public class BaseServiceTest {

    protected static UserDetailsImpl userDetails;
    protected static User user1;
    protected static User user2;
    private static boolean dataInit;
    private static UserRepository userRepoStatic;
    private static DeptRepository deptRepoStatic;
    private static RoleRepository roleRepoStatic;
    private static RoleService roleServiceStatic;
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private DeptRepository deptRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterAll
    static void afterAll() {
        userRepoStatic.delete(new QueryWrapper<>());
        roleRepoStatic.delete(new QueryWrapper<>());
        deptRepoStatic.delete(new QueryWrapper<>());
        dataInit = false;
    }

    @BeforeEach
    public void baseSetUp() {
        if (!dataInit) {
            Dept dept1 = DeptBuilder.aDept().name("神湾分局").build();
            deptRepo.insert(dept1);
            Role role1 = RoleBuilder.aRole().name("超级管理员").build();
            roleRepo.insert(role1);

            user1 = UserBuilder.anUser()
                    .username("admin")
                    .email("123@qq.com")
                    .phone("13412334452")
                    .dept(dept1)
                    .role(role1)
                    .password(passwordEncoder.encode("admin"))
                    .build();
            userRepo.insert(user1);

            user2 = UserBuilder.anUser()
                    .username("13412334452")
                    .email("13412334452@zsc.edu.cn")
                    .phone("13412334452")
                    .password(passwordEncoder.encode("user1"))
                    .build();
            userRepo.insert(user2);
            userDetails = UserDetailsImpl.from(user1);

            dataInit = true;
            deptRepoStatic = deptRepo;
            roleRepoStatic = roleRepo;
            userRepoStatic = userRepo;
            roleServiceStatic = roleService;
        }
    }
}
