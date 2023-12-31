package com.aral.controller;

import com.aral.dto.request.UserProfileSaveRequestDto;
import com.aral.mapper.IUserProfileMapper;
import com.aral.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userprofile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto){
        userProfileService.save(IUserProfileMapper.INSTANCE.fromUserProfileDto(dto));
        return ResponseEntity.ok().build();
    }
}
