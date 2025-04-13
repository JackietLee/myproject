package com.handsome.jay.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author ShiLei
 * @version 1.0.0
 * @date 2021/3/10 13:15
 * @description 自定义用户信息
 */
public class SecurityUserDetails extends User implements Serializable {

    private Long userId;
    private List<String> roles;

    public SecurityUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId, List<String> roles) {
        super(username, password, authorities);
        this.userId = userId;
        this.roles = roles;
    }

    public SecurityUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long userId, List<String> roles) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

