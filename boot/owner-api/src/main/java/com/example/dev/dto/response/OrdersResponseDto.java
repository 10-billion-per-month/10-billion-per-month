package com.example.dev.dto.response;

import com.example.dev.dto.OrderDto;
import com.example.dev.dto.request.OrdersRequestDto;
import com.example.dev.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrdersResponseDto {

    private Long orderId;
    private Integer orderTotalPrice;
    private OrderStatus orderStatus;
    private Long qrcodeId;
    private Long storeId;

    public static OrdersResponseDto from(OrderDto orderDto) {
        return OrdersResponseDto.builder()
                .orderId(orderDto.getOrderId())
                .orderTotalPrice(orderDto.getOrderTotalPrice())
                .orderStatus(orderDto.getOrderStatus())
                .qrcodeId(orderDto.getQrcodeId())
                .storeId(orderDto.getStoreId())
                .build();
    }
}
