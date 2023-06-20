package com.example.dev.repository;

import com.example.dev.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// for test
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
