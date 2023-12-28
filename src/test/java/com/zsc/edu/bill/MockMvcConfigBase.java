package com.zsc.edu.bill;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsc.edu.bill.domain.DeptBuilder;
import com.zsc.edu.bill.domain.RoleBuilder;
import com.zsc.edu.bill.domain.UserBuilder;
import com.zsc.edu.bill.framework.security.CustomAccessDeniedHandler;
import com.zsc.edu.bill.framework.security.CustomAuthenticationFailureHandler;
import com.zsc.edu.bill.framework.security.UserDetailsImpl;
import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.mapper.RoleMapper;
import com.zsc.edu.bill.modules.system.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pengzheng
 */
@ExtendWith(SpringExtension.class)
//@AutoConfigureRestDocs
//@ActiveProfiles("test")
abstract public class MockMvcConfigBase {

    protected static UserDetailsImpl userDetails;
    protected static User user;
    @MockBean
    protected DataSource dataSource;
    @MockBean
    protected SessionRegistry sessionRegistry;
    @MockBean
    protected UserMapper userMapper;
    @MockBean
    protected RoleMapper roleMapper;
    @MockBean
    protected CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @MockBean
    protected CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeAll
    public static void setup() {
        Dept dept = DeptBuilder.aDept().name("Platform").build();
        Role role = RoleBuilder.aRole().authorities(new HashSet<>(Arrays.asList(Authority.values()))).build();
        user = UserBuilder.anUser().username("admin").dept(dept).role(role).build();
        userDetails = UserDetailsImpl.from(user);
    }
}
