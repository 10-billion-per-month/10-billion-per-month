package com.example.dev.service;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.entity.Order;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.OrderDetailRepository;
import com.example.dev.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderReadService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;


    /**
     * 주문 상세 목록 조회
     * @param dto
     * @param pageable
     * @return
     */
    public Page<OrderDetailDto> getOrderDetails(OrderDetailDto dto, Pageable pageable) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("주문을 찾을 수 없습니다. orderId = %s", dto.getOrderId())));
        return orderDetailRepository.findAllByOrder_OrderId(order.getOrderId(), pageable).map(OrderDetailDto::from);
    }
}
