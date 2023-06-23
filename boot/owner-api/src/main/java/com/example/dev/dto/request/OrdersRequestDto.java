package com.example.dev.dto.request;

import com.example.dev.dto.OrderDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrdersRequestDto {

    @Min(1)
    private Long qrcodeId;
    private String orderStatus;

    public OrderDto toDto() {
        return OrderDto.builder()
                .qrcodeId(qrcodeId)
                .orderStatus(orderStatus)
                .build();
    }
}
