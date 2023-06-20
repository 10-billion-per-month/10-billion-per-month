package com.example.dev.repository;

import com.example.dev.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Qrcode에 진행중인 주문이 있는지 조회
     * @param qrcodeId
     * @param orderStatus
     * @return
     */
    Optional<Order> findByQrcodeQrcodeIdAndOrderStatusNot(Long qrcodeId, String orderStatus);
}
