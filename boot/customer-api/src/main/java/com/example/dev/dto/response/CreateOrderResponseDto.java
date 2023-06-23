package com.example.dev.dto.response;

import com.example.dev.dto.OrderDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateOrderResponseDto {

    private Long orderDetailId;
    private Long orderId;
    private Long menuId;
    private String menuName;
    private Integer orderMenuPrice;
    private Integer orderDetailCount;
    private Integer orderDetailTotalPrice;
    private String orderDetailStatus;

    public static CreateOrderResponseDto from(OrderDetailDto dto) {
        return CreateOrderResponseDto.builder()
                .orderDetailId(dto.getOrderDetailId())
                .orderId(dto.getOrderId())
                .menuId(dto.getMenuId())
                .menuName(dto.getMenuName())
                .orderMenuPrice(dto.getOrderMenuPrice())
                .orderDetailCount(dto.getOrderDetailCount())
                .orderDetailTotalPrice(dto.getOrderDetailTotalPrice())
                .orderDetailStatus(dto.getOrderDetailStatus())
                .build();
    }
}
