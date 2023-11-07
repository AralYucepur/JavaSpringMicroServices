package com.aral.config.security;

import com.aral.exception.AuthServiceException;
import com.aral.exception.ErrorType;
import com.aral.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter{

    
    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    JwtUserDetails jwtUserDetails;


    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        final String authHeaderParameters = request.getHeader("Authorization");
        if(authHeaderParameters != null && authHeaderParameters.startsWith("Bearer")
                && SecurityContextHolder.getContext().getAuthentication() == null){
            String token = authHeaderParameters.substring(7);
            Optional<Long> authid = jwtTokenManager.getByIdFromToken(token);
            if(authid.isPresent()){
                UserDetails userDetails = jwtUserDetails.getUserByAuthId(authid.get());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else {
                throw new AuthServiceException(ErrorType.JWT_INVALID_TOKEN);
            }
        }
        filterChain.doFilter(request,response);
        }



    }

