package com.example.dev.dto.request;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.entity.Menu;
import lombok.Getter;

@Getter
public class CreateOrderDetailRequestDto {

    private Long menuId;
    private Integer orderMenuPrice;
    private Integer orderDetailCount;
    private Integer orderDetailTotalPrice;

    public OrderDetailDto toDto() {
        return OrderDetailDto.builder()
                .menuId(menuId)
                .orderMenuPrice(orderMenuPrice)
                .orderDetailCount(orderDetailCount)
                .orderDetailTotalPrice(orderDetailTotalPrice)
                .build();
    }
}
