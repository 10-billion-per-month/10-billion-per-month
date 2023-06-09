package com.example.qtable.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class OrderDetail extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;
    private Long orderId;
    private Long menuId;
    private Integer orderMenuPrice;
    private Integer orderCount;
    private Integer orderTotalPrice;
    private String orderDetailStatus;

    @Builder
    public OrderDetail(Long orderId, Long menuId, Integer orderMenuPrice, Integer orderCount, Integer orderTotalPrice, String orderDetailStatus) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.orderMenuPrice = orderMenuPrice;
        this.orderCount = orderCount;
        this.orderTotalPrice = orderTotalPrice;
        this.orderDetailStatus = orderDetailStatus;
    }
}
