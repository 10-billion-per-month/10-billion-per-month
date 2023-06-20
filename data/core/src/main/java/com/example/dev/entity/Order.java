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
@Table(name = "orders")
@Where(clause = "delete_at = 'N'")
public class Order extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Integer orderTotalPrice;
    private String orderStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qrcode_id")
    private Qrcode qrcode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder
    public Order(Integer orderTotalPrice, String orderStatus, Qrcode qrcode, Store store) {
        this.orderTotalPrice = orderTotalPrice;
        this.orderStatus = orderStatus;
        this.qrcode = qrcode;
        this.store = store;
    }
}
