package com.example.dev.dto.request;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.dto.OrderDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderRequestDto {

    private Long qrcodeId;
    private List<CreateOrderDetailRequestDto> orderDetailList;

    public OrderDto toDto() {
        return OrderDto.builder()
                .qrcodeId(qrcodeId)
                .orderDetailList(orderDetailList.stream().map(CreateOrderDetailRequestDto::toDto).toList())
                .build();
    }
}
