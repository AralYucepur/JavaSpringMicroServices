package com.aral.controller;

import com.aral.dto.request.CreateProfileRequestDto;

import com.aral.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.aral.constants.RestApi.*;

@RestController
@RequestMapping(USERPROFILE)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(CREATEPROFILE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createProfile(@RequestBody @Valid CreateProfileRequestDto dto){

        return ResponseEntity.ok(userProfileService.createProfile(dto));

    }
    @GetMapping("/userdeneme")
    @CrossOrigin("*")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> userdeneme(){

        return ResponseEntity.ok("başarılı");
    }

    @GetMapping("/say")
    @CrossOrigin("*")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> JwtTry(){

        return ResponseEntity.ok("Deneme başarılı.");

    }


}


