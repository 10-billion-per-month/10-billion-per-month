package com.example.dev.repository;

import com.example.dev.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// for test
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
