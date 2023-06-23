package com.example.dev.dto.response;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderDetailsResponseDto {

    private Long orderDetailId;
    private Long orderId;
    private Long menuId;
    private Integer orderMenuPrice;
    private Integer orderDetailCount;
    private Integer orderDetailTotalPrice;
    private String orderDetailStatus;

    public static OrderDetailsResponseDto from(OrderDetailDto orderDetailDto) {
        return OrderDetailsResponseDto.builder()
                .orderDetailId(orderDetailDto.getOrderDetailId())
                .orderId(orderDetailDto.getOrderId())
                .menuId(orderDetailDto.getMenuId())
                .orderMenuPrice(orderDetailDto.getOrderMenuPrice())
                .orderDetailCount(orderDetailDto.getOrderDetailCount())
                .orderDetailTotalPrice(orderDetailDto.getOrderDetailTotalPrice())
                .orderDetailStatus(orderDetailDto.getOrderDetailStatus())
                .build();
    }
}
