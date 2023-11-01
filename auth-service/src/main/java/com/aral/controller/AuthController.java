package com.aral.controller;

import com.aral.repository.entity.Auth;
import com.aral.repository.entity.State;
import com.aral.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aral.constants.RestApi.*;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(DOLOGIN)
    public ResponseEntity<Boolean> doLogin(String username, String password){
        return ResponseEntity.ok(true);
    }
    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(String username,String password,String repassword){
        authService.save(Auth.builder()
                .username(username)
                .password(password)
                .state(State.ACTIVE).build());
        return ResponseEntity.ok(true);
    }
}
