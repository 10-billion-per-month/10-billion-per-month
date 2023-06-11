package com.example.dev.repository;

import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findAllByOwner(Owner owner);
}
