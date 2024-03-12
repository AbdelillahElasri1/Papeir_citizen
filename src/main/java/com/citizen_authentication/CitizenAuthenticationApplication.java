package com.citizen_authentication;

import com.citizen_authentication.models.repositories.RoleRepository;
import com.citizen_authentication.models.repositories.UserRepository;
import com.citizen_authentication.web.service.IUserService;
import com.citizen_authentication.models.entities.Role;
import com.citizen_authentication.models.enums.RoleName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CitizenAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitizenAuthenticationApplication.class, args);
	}

	@Bean
	CommandLineRunner run (IUserService iUserService , RoleRepository roleRepository , UserRepository userRepository , PasswordEncoder passwordEncoder)
	{
		return args ->
			{
				iUserService.saveRole(new Role(RoleName.USER));
				iUserService.saveRole(new Role(RoleName.ADMIN));
		};
	}

}
