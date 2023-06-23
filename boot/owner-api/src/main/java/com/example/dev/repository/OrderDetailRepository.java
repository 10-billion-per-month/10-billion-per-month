package com.example.dev.repository;

import com.example.dev.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Page<OrderDetail> findAllByOrder_OrderId(long orderId, Pageable pageable);
}
