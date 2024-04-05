package com.citizen_authentication.web.controller;

import com.citizen_authentication.models.dto.auth.request.LoginDto;
import com.citizen_authentication.models.dto.auth.request.RegisterDto;
import com.citizen_authentication.models.dto.response.UserResponse;
import com.citizen_authentication.web.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> userResponseList = iUserService.getAllUsers();
        return ResponseEntity.ok(userResponseList);
    }
    @GetMapping("/admins")
    public ResponseEntity<List<UserResponse>> getAllAdmins(){
        List<UserResponse> adminList = iUserService.getAllAdmin();
        return ResponseEntity.ok(adminList);
    }

    @GetMapping("/citizens")
    public ResponseEntity<List<UserResponse>> getAllCitizens(){
        List<UserResponse> citizenList = iUserService.getAllCitizen();
        return ResponseEntity.ok(citizenList);
    }
}
