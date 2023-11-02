package com.aral.controller;

import com.aral.dto.request.RegisterRequestDto;
import com.aral.dto.response.RegisterResponseDto;
import com.aral.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.aral.constants.RestApi.*;


@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(DOLOGIN)
    public ResponseEntity<Boolean> doLogin(String username, String password){

        return ResponseEntity.ok(true);
    }
    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){

        return ResponseEntity.ok(authService.save(dto));
    }
}
