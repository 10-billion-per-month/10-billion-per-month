package com.example.dev.dto;

import com.example.dev.entity.Qrcode;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
}
