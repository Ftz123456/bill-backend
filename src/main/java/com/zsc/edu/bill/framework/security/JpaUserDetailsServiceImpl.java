package com.zsc.edu.bill.framework.security;

import com.zsc.edu.bill.modules.system.entity.User;
import com.zsc.edu.bill.modules.system.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author harry_yao
 */
@AllArgsConstructor
@Service
public class JpaUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
//                .orElseThrow(() ->
//            new UsernameNotFoundException("用户 '" + username + "' 不存在!")
//        );
//        user.getIdentities().stream().filter(identity -> identity.role.enableState == EnableState.启用)
//            .forEach(identity -> Hibernate.initialize(identity.role.authorities));
        return UserDetailsImpl.from(user);
    }
}
