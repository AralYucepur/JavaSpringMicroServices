package com.aral.config.security;


import com.aral.repository.entity.Auth;
import com.aral.service.AuthService;
import com.aral.service.RoleService;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {


    AuthService authService;
    RoleService roleService;


    public JwtUserDetails(@Lazy AuthService authService, RoleService roleService) {

        this.authService = authService;
        this.roleService = roleService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails getUserByAuthId(Long authid){

        Optional<Auth> auth = authService.findById(authid);
        if(auth.isEmpty()) return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        roleService.findByAuthid(authid).forEach(x-> authorities.add(new SimpleGrantedAuthority((x.getRole()))));

        return User.builder()
                .username(authid.toString())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorities)
                .build();

    }


}
