package com.citizen_authentication.web.controller;

import com.citizen_authentication.models.dto.auth.request.LoginDto;
import com.citizen_authentication.models.dto.auth.request.RegisterDto;
import com.citizen_authentication.web.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {
    private final IUserService iUserService;


    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto) {
        return iUserService.register(registerDto);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        return iUserService.authenticate(loginDto);
    }
}
