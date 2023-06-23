package com.example.dev.dto;

import com.example.dev.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long orderId;
    private Integer orderTotalPrice;
    private String orderStatus;
    private Long qrcodeId;
    private Long storeId;
    private List<OrderDetailDto> orderDetailList;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderTotalPrice(order.getOrderTotalPrice())
                .orderStatus(order.getOrderStatus())
                .qrcodeId(order.getQrcode().getQrcodeId())
                .storeId(order.getStore().getStoreId())
                .build();
    }
}
