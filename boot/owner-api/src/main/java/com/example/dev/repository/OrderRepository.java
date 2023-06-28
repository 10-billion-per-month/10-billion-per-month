package com.example.dev.repository;

import com.example.dev.entity.Order;
import com.example.dev.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByQrcodeQrcodeIdAndOrderStatus(long qrcodeId, OrderStatus orderStatus, Pageable pageable);
    Page<Order> findAllByQrcodeQrcodeId(long qrcodeId, Pageable pageable);
}
