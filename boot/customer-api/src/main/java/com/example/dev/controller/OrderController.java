package com.example.dev.controller;

import com.example.dev.dto.request.CreateOrderRequestDto;
import com.example.dev.dto.response.CreateOrderResponseDto;
import com.example.dev.service.OrderWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderWriteService orderWriteService;

    /**
     * 주문 하기
     * @param requestDto
     * @return 주문 상세 목록 반환
     */
    @PostMapping("/v1/createOrder")
    public List<CreateOrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        return orderWriteService.createOrder(requestDto.toDto()).stream().map(CreateOrderResponseDto::from).collect(Collectors.toList());
    }
}
