package com.aral.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class AuthServiceSecurityConfig {

    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/v3/api-docs/**","/swagger-ui/**","/v1/dev/auth/dologin","/v1/dev/auth/register")
                .permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity.addFilterBefore(getJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();

    }
}
