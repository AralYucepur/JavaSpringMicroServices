package com.aral.config.security;


import com.aral.repository.enums.EState;
import com.aral.service.AuthService;

import com.aral.utility.JwtTokenManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JwtUserDetails implements UserDetailsService {


    AuthService authService;
    JwtTokenManager jwtTokenManager;


    public JwtUserDetails(@Lazy AuthService authService, JwtTokenManager jwtTokenManager) {

        this.authService = authService;
        this.jwtTokenManager = jwtTokenManager;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails getUserByToken(String token){


        Optional<String> role = jwtTokenManager.getRoleFromToken(token);
        Optional<String> stateString = jwtTokenManager.getStateFromToken(token);
        Optional<EState> state = stateString.map(s -> EState.valueOf(s.toUpperCase()));
        boolean disabled = state.map(s -> s != EState.ACTIVE).orElse(true);
        boolean accountLocked = state.map(s -> s == EState.BLOCKED).orElse(false);
        // Role is not a List but can change later.
        List<GrantedAuthority> authorities = role.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        return User.builder()
                .username(jwtTokenManager.getByIdFromToken(token).toString())
                .password("")
                .disabled(disabled)
                .accountLocked(accountLocked)
                .accountExpired(false)
                .authorities(authorities)
                .build();

    }


}
