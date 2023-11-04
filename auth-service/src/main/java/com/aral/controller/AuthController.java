package com.aral.controller;

import com.aral.dto.request.DoLoginRequestDto;
import com.aral.dto.request.RegisterRequestDto;
import com.aral.dto.response.DoLoginResponseDto;
import com.aral.dto.response.RegisterResponseDto;
import com.aral.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.aral.constants.RestApi.*;


@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(DOLOGIN)
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){


        return ResponseEntity.ok(authService.doLogin(dto));
    }
    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){

        return ResponseEntity.ok(authService.save(dto));
    }

    @GetMapping("/say")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> JwtTry(){

        return ResponseEntity.ok("Deneme başarılı.");

    }
}
