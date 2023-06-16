package com.example.dev.repository;

import com.example.dev.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByCategory_CategoryId(Long categoryId, Pageable pageable);
    Page<Menu> findAllByStore_StoreId(Long storeId, Pageable pageable);
}
