package com.aral.controller;


import com.aral.repository.entity.Role;
import com.aral.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    @PostMapping("/saverole")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Void> saveRole(String RoleName, Long authid){
        roleService.save(Role.builder()
                .role(RoleName)
                .authid(authid)
                .build());

        return ResponseEntity.ok().build();

    }
}
