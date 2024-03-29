package com.citizen_authentication.web.service;

import com.citizen_authentication.models.entities.Role;
import com.citizen_authentication.models.entities.User;
import com.citizen_authentication.models.dto.auth.request.LoginDto;
import com.citizen_authentication.models.dto.auth.request.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> authenticate(LoginDto loginDto);


    ResponseEntity<?> register (RegisterDto registerDto);
    Role saveRole(Role role);
    User saverUser (User user) ;
}
