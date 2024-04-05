package com.citizen_authentication.models.repositories;

import com.citizen_authentication.models.entities.ActDemand;
import com.citizen_authentication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActDemandRepository extends JpaRepository<ActDemand, Integer> {
}
