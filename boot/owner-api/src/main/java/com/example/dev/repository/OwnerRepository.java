package com.example.dev.repository;

import com.example.dev.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByOwnerEmail(String ownerEmail);

    boolean existsOwnerByOwnerEmail(String ownerEmail);

}
