package com.example.dev.repository;

import com.example.dev.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByStore_StoreId(Long storeId, Pageable pageable);
    Optional<Category> findByStore_StoreIdAndCategoryId(Long storeId, Long categoryId);
}
