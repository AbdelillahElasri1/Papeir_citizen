package com.citizen_authentication.models.repositories;

import com.citizen_authentication.models.entities.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Documents, Integer> {
}
