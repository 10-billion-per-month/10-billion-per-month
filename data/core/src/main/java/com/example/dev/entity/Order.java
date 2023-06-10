package com.example.dev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
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
