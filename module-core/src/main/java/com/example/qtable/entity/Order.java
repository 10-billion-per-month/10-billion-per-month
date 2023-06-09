package com.example.qtable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Integer orderTotalPrice;
    private String orderStatus;
    private Long qrcodeId;
    private Long storeId;

    @Builder
    public Order(Integer orderTotalPrice, String orderStatus, Long qrcodeId, Long storeId) {
        this.orderTotalPrice = orderTotalPrice;
        this.orderStatus = orderStatus;
        this.qrcodeId = qrcodeId;
        this.storeId = storeId;
    }
}
