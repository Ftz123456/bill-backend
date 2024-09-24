package com.zsc.edu.bill.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.edu.bill.domain.RoleBuilder;
import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.repo.RoleRepository;
import com.zsc.edu.bill.modules.system.service.RoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author harry yao
 */
//@ActiveProfiles("test")
@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService service;

    @Resource
    private RoleRepository repo;

    private Role role1;
    private Role role2;

    private Role Role3;
    private Role Role4;

    @BeforeEach
    void setUp() {
        role1 = RoleBuilder.aRole().name("超级管理员").build();
        repo.insert(role1);
        role2 = RoleBuilder.aRole().name("普通用户").build();
        repo.insert(role2);
    }

    @AfterEach
    void tearDown() {
        repo.delete(new QueryWrapper<>());
    }

    @Test
    void list() {
        assertEquals(2, service.list().size());
        assertEquals(1, service.list(new LambdaQueryWrapper<Role>().like(Role::getName, "普通用户")).size());
        assertEquals(1, service.list(new LambdaQueryWrapper<Role>().eq(Role::getName, role1.getName())).size());
    }

    @Test
    void create() {
//        Role Role  = new Role();
//        Role.setName("东菱经销商5");
//        Role.setRemark("remark...");
//        Role.setPid(Role1.id);
        RoleDto dto = new RoleDto();
        dto.setName("东菱经销商5");
        dto.setRemark("remark...");
        dto.setAuthorities(new HashSet<>(Arrays.asList(Authority.values())));
        Role Role = service.create(dto);
        assertNotNull(Role.getId());
        assertEquals(3, service.list().size());
        // 不能创建其他已存在的同名同代码部门
//        assertThrows(ConstraintException.class, () -> service.create(dto2));
    }

    @Test
    void update() {
        RoleDto dto = new RoleDto();
        dto.setName("超级管理员2");
        dto.setRemark("remark...");
        dto.setAuthorities(Set.of(Authority.ROLE_UPDATE, Authority.DEPT_UPDATE));
        assertTrue(service.edit(dto, role2.id));
    }


}
