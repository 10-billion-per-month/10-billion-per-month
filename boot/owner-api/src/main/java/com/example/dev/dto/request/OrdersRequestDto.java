package com.example.dev.dto.request;

import com.example.dev.dto.OrderDto;
import com.example.dev.entity.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrdersRequestDto {

    @Min(1)
    private Long qrcodeId;
    private OrderStatus orderStatus;

    public OrderDto toDto() {
        return OrderDto.builder()
                .qrcodeId(qrcodeId)
                .orderStatus(orderStatus)
                .build();
    }
}
