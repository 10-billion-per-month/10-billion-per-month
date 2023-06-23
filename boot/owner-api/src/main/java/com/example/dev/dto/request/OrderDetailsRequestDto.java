package com.example.dev.dto.request;

import com.example.dev.dto.OrderDetailDto;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class OrderDetailsRequestDto {

    @Min(1)
    private Long orderId;

    public OrderDetailDto toDto() {
        return OrderDetailDto.builder()
                .orderId(orderId)
                .build();
    }

}
