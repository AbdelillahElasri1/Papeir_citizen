package com.citizen_authentication.service;

import com.citizen_authentication.models.dto.auth.request.LoginDto;
import com.citizen_authentication.models.dto.auth.request.RegisterDto;
import com.citizen_authentication.models.entities.Role;
import com.citizen_authentication.models.entities.User;
import com.citizen_authentication.models.enums.RoleName;
import com.citizen_authentication.models.repositories.RoleRepository;
import com.citizen_authentication.models.repositories.UserRepository;
import com.citizen_authentication.security.utils.JwtUtilities;
import com.citizen_authentication.web.service.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@SpringBootTest
public class UserServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtilities jwtUtilities;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAuthenticate_Success() {
        // Mocking
        LoginDto loginDto = new LoginDto("test@example.com", "password");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        Role role = new Role();
        role.setRoleName(RoleName.USER);
        user.setRoles(Collections.singletonList(role));
        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(user));
        when(jwtUtilities.generateToken(user.getUsername(), Collections.singletonList(role.getRoleName()))).thenReturn("token");

        // Test
        ResponseEntity<?> response = userService.authenticate(loginDto);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User login successful!", ((Map<String, String>) response.getBody()).get("message"));
        assertEquals("token", ((Map<String, String>) response.getBody()).get("token"));
    }

    @Test
    public void testAuthenticate_UserNotFound() {
        // Mocking
        LoginDto loginDto = new LoginDto("nonexistent@example.com", "password");
        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.empty());

        // Test and Verify
        UsernameNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.authenticate(loginDto));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testRegister_Success() {
        // Mocking
        RegisterDto registerDto = new RegisterDto("test@example.com", "password", "John", "Doe", "user");
        when(userRepository.existsByEmail(registerDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encodedPassword");
        when(roleRepository.findByRoleName(RoleName.USER)).thenReturn(mock(Role.class));
        when(jwtUtilities.generateToken(registerDto.getEmail(), Collections.singletonList("USER"))).thenReturn("token");

        // Test
        ResponseEntity<?> response = userService.register(registerDto);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bearer token", ((String) response.getBody()));
    }

}
