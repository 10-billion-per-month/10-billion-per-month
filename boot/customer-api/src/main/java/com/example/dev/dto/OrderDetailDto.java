package com.example.dev.dto;

import com.example.dev.entity.Menu;
import com.example.dev.entity.Order;
import com.example.dev.entity.OrderDetail;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderDetailDto {

    private Long orderDetailId;
    private Long orderId;
    private Long menuId;
    private String menuName;
    private Integer orderMenuPrice;
    private Integer orderDetailCount;
    private Integer orderDetailTotalPrice;
    private String orderDetailStatus;

    public OrderDetail toEntity(Order order, Menu menu) {
        return OrderDetail.builder()
                .order(order)
                .menu(menu)
                .orderMenuPrice(orderMenuPrice)
                .orderDetailCount(orderDetailCount)
                .orderDetailTotalPrice(orderDetailTotalPrice)
                .orderDetailStatus(orderDetailStatus)
                .build();
    }

    public static OrderDetailDto from(OrderDetail orderDetail) {
        return OrderDetailDto.builder()
                .orderDetailId(orderDetail.getOrderDetailId())
                .orderId(orderDetail.getOrder().getOrderId())
                .menuId(orderDetail.getMenu().getMenuId())
                .menuName(orderDetail.getMenu().getMenuName())
                .orderMenuPrice(orderDetail.getOrderMenuPrice())
                .orderDetailCount(orderDetail.getOrderDetailCount())
                .orderDetailTotalPrice(orderDetail.getOrderDetailTotalPrice())
                .orderDetailStatus(orderDetail.getOrderDetailStatus())
                .build();
    }

}
