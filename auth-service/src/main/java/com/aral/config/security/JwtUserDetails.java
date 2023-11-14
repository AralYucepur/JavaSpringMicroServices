package com.aral.config.security;


import com.aral.service.AuthService;
import com.aral.service.RoleService;

import com.aral.utility.JwtTokenManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetails implements UserDetailsService {


    AuthService authService;
    RoleService roleService;
    JwtTokenManager jwtTokenManager;


    public JwtUserDetails(@Lazy AuthService authService, RoleService roleService, JwtTokenManager jwtTokenManager) {

        this.authService = authService;
        this.roleService = roleService;
        this.jwtTokenManager = jwtTokenManager;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails getUserByToken(String token){

        List<String> roles = jwtTokenManager.getRolesFromToken(token).orElse(Collections.emptyList());

        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        return User.builder()
                .username(jwtTokenManager.getByIdFromToken(token).toString())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorities)
                .build();

    }


}
