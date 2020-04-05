package com.blog.config.sercurity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BlogUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("UserName is Blank.");
        }
        if (StringUtils.equals("aa", username)) {
            throw new UsernameNotFoundException("user not found.");
        }
        return new org.springframework.security.core.userdetails.User(username, new BCryptPasswordEncoder().encode(username), getAuthorities());
    }

    private Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("USER"));
        authList.add(new SimpleGrantedAuthority("ADMIN"));
        authList.add(new SimpleGrantedAuthority("DBA"));
        return authList;
    }
}