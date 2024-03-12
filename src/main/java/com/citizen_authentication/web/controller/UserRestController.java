package com.citizen_authentication.web.controller;

import com.citizen_authentication.models.dto.request.LoginDto;
import com.citizen_authentication.models.dto.request.RegisterDto;
import com.citizen_authentication.web.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService iUserService;


    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto) {
        return iUserService.register(registerDto);
    }


    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto) {
        return iUserService.authenticate(loginDto);
    }
}
