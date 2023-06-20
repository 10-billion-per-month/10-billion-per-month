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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;
    private Integer orderMenuPrice;
    private Integer orderDetailCount;
    private Integer orderDetailTotalPrice;
    private String orderDetailStatus;

    @Builder
    public OrderDetail(Order order, Menu menu, Integer orderMenuPrice, Integer orderDetailCount, Integer orderDetailTotalPrice, String orderDetailStatus) {
        this.order = order;
        this.menu = menu;
        this.orderMenuPrice = orderMenuPrice;
        this.orderDetailCount = orderDetailCount;
        this.orderDetailTotalPrice = orderDetailTotalPrice;
        this.orderDetailStatus = orderDetailStatus;
    }
}
