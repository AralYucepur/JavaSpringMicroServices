package com.aral.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallBackController {
    @GetMapping("/fallbackauth")
    public ResponseEntity<String> fallBackAuth(){
        return ResponseEntity.ok("Servis şu an bakımda.Lütfen daha sonra tekrar deneyiniz.");
    }
}
