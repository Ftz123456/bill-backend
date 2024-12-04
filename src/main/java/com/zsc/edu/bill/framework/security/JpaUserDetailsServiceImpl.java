package com.zsc.edu.bill.framework.security;

import com.zsc.edu.bill.exception.ApiException;
import com.zsc.edu.bill.exception.StateException;
import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.RoleAuthority;
import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.repo.RoleAuthoritiesRepository;
import com.zsc.edu.bill.modules.system.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author harry_yao
 */
@AllArgsConstructor
@Service
public class JpaUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;
    private  final RoleAuthoritiesRepository roleAuthoritiesRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.selectByUsername(username);
        if (!user.getEnabled()) {
            throw new StateException("用户 '" + username + "' 已被禁用！请联系管理员");
        }

        List<RoleAuthority> roleAuthorities= roleAuthoritiesRepository.selectByRoleId(user.getRoleId());
        user.role.authorities=roleAuthorities.stream()
                .map(i -> Authority.valueOf(i.getAuthority()))
                .collect(Collectors.toSet());



//                .orElseThrow(() ->
//            new UsernameNotFoundException("用户 '" + username + "' 不存在!")
//        );
//        user.getIdentities().stream().filter(identity -> identity.role.enableState == EnableState.启用)
//            .forEach(identity -> Hibernate.initialize(identity.role.roleAuthorities));
        return UserDetailsImpl.from(user);
    }
}
