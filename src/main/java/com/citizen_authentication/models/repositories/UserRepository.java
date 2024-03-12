package com.citizen_authentication.models.repositories;

import com.citizen_authentication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
