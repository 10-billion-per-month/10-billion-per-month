package com.example.dev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "delete_at = 'N'")
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
