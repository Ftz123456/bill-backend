package com.zsc.edu.bill.framework.security;

import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author harry yao
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties("password")
public class UserDetailsImpl implements UserDetails {

    public Long id;
    public String username;
    public String password;
    public Boolean enabled;

    public Dept dept;
    public Role role;
    public Set<Authority> authorities;

    public static UserDetailsImpl from(User user) {
        Set<Authority> authorities = user.role.authorities;
        return new UserDetailsImpl(
                user.id,
                user.username,
                user.password,
                user.enabled,
                user.dept,
                user.role,
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
