package com.zsc.edu.bill.service;

import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.repo.RoleAuthoritiesRepository;
import com.zsc.edu.bill.modules.system.repo.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ftz
 * 创建时间:29/12/2023 上午11:21
 * 描述: TODO
 */
@SpringBootTest
public class UserServiceTest {
    @Resource
    private RoleAuthoritiesRepository roleAuthoritiesRepository;
    @Resource
    private  UserRepository userRepository;
    @Test
    void test() {
        User user=userRepository.selectByUsername("admin");
        List<RoleAuthority> authorities= roleAuthoritiesRepository.selectByRoleId(user.getRoleId());
        Set<Authority> authorities1=new HashSet<>();
        authorities.stream().forEach(authority ->authorities1.add(Authority.valueOf(authority.getAuthority())));
        user.role.authorities=authorities1;
        System.out.println(user);

    }
    @Test
    void test1() {
        User user=userRepository.selectByUsername("admin");
        System.out.println(user);

    }
}
