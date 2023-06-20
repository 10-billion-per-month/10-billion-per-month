package com.example.dev.service;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.dto.OrderDto;
import com.example.dev.entity.*;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderWriteService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final QrcodeRepository qrcodeRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    /**
     * 주문상세등록
     * @param dto
     */
    public void createOrderDetail(OrderDto dto) {
        // 큐알코드 아이디 검증
        Qrcode qrcode = qrcodeRepository.findById(dto.getQrcodeId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 큐알코드입니다. qrcodeId = %s", dto.getQrcodeId())));

        // 진행중인 주문 조회 -> 없을 경우 신규 주문 생성
        Order order = orderRepository.findByQrcodeQrcodeIdAndOrderStatusNot(qrcode.getQrcodeId(), "COMPLETE")
                .orElse(createOrder(qrcode));

        // 주문 상세 목록 -> entity
        List<OrderDetail> orderDetails = dto.getOrderDetailList().stream().map(
                orderDetailDto -> orderDetailDto.toEntity(order, menuRepository.findById(orderDetailDto.getMenuId()).get())
        ).collect(Collectors.toList());

        // 주문 상세 목록 저장
        orderDetailRepository.saveAll(orderDetails);
    }

    /**
     * 신규 주문 생성
     * @param qrcode
     * @return
     */
    public Order createOrder(Qrcode qrcode) {
        return orderRepository.saveAndFlush(Order.builder()
                        .qrcode(qrcode)
                        .store(qrcode.getStore())
                .build());
    }
}
