package com.citizen_authentication.web.service.implementation;

import com.citizen_authentication.models.dto.response.UserResponse;
import com.citizen_authentication.security.utils.JwtUtilities;
import com.citizen_authentication.models.dto.auth.response.BearerToken;
import com.citizen_authentication.models.dto.auth.request.LoginDto;
import com.citizen_authentication.models.dto.auth.request.RegisterDto;
import com.citizen_authentication.models.entities.Role;
import com.citizen_authentication.models.entities.User;
import com.citizen_authentication.models.enums.RoleName;
import com.citizen_authentication.models.repositories.RoleRepository;
import com.citizen_authentication.models.repositories.UserRepository;
import com.citizen_authentication.web.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> authenticate(LoginDto loginDto) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),loginDto.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userRepository.findByEmail(authentication.getName()).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
            List<String> rolesNames = new ArrayList<>();
            user.getRoles().forEach(r-> rolesNames.add(r.getRoleName()));
            String token = jwtUtilities.generateToken(user.
                getUsername(),rolesNames);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User login successful!");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER);
        } else {
            User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            String myrole = "user";
            if (registerDto.getUserRole().equals("") || registerDto.getUserRole().equals("user")) {
                myrole = "USER";
            }

            if (registerDto.getUserRole().equals("admin")) {
                myrole = "ADMIN";
            }
                Role role = roleRepository.findByRoleName(RoleName.valueOf(myrole));
                user.setUserRole(registerDto.getUserRole());
                user.setRoles(Collections.singletonList(role));
                userRepository.save(user);
                String token = jwtUtilities.generateToken(registerDto.getEmail(),Collections.singletonList(role.getRoleName()));
                return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK);
                                    }
        }


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User saverUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> modelMapper.map(user,UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getAllCitizen() {
        List<User> citizenList = userRepository.findByUserRole("user");
        return citizenList.stream().map(citizen -> modelMapper.map(citizen, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getAllAdmin() {
        List<User> adminList = userRepository.findByUserRole("admin");
        return adminList.stream().map(admin -> modelMapper.map(admin, UserResponse.class)).collect(Collectors.toList());
    }
}
