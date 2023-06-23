package com.example.dev.controller;

import com.example.dev.dto.request.OrderDetailsRequestDto;
import com.example.dev.dto.response.OrderDetailsResponseDto;
import com.example.dev.service.OrderReadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderReadService orderReadService;

    /**
     * 주문 상세 목록 조회
     * @param requestDto
     * @param pageable
     * @return
     */
    @GetMapping("/v1/orderDetails")
    public Page<OrderDetailsResponseDto> getOrderDetails(@Valid OrderDetailsRequestDto requestDto, @PageableDefault(size = 10, sort = "orderDetailId", direction = Sort.Direction.ASC) Pageable pageable) {
        return orderReadService.getOrderDetails(requestDto.toDto(), pageable).map(OrderDetailsResponseDto::from);
    }
}
