package com.citizen_authentication.models.repositories;

import com.citizen_authentication.models.entities.Role;
import com.citizen_authentication.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
