package com.example.dev.service;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.dto.OrderDto;
import com.example.dev.dto.response.OrdersResponseDto;
import com.example.dev.entity.Order;
import com.example.dev.entity.Qrcode;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.OrderDetailRepository;
import com.example.dev.repository.OrderRepository;
import com.example.dev.repository.QrcodeRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderReadService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final QrcodeRepository qrcodeRepository;



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

    public Page<OrderDto> getOrders(OrderDto dto, Pageable pageable) {
        Qrcode qrcode = qrcodeRepository.findById(dto.getQrcodeId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("큐알코드를 찾을 수 없습니다. qrcodeId = %s", dto.getQrcodeId())));

        if(StringUtils.isBlank(dto.getOrderStatus())) {
            return orderRepository.findAllByQrcodeQrcodeId(qrcode.getQrcodeId(), pageable).map(OrderDto::from);
        } else {
            return orderRepository.findAllByQrcodeQrcodeIdAndOrderStatus(qrcode.getQrcodeId(), dto.getOrderStatus(), pageable).map(OrderDto::from);
        }

    }
}
