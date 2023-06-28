package com.example.dev.service;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.dto.OrderDto;
import com.example.dev.entity.*;
import com.example.dev.repository.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@SpringBootTest
public class OrderReadServiceTest {

    @Autowired
    QrcodeRepository qrcodeRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DatabaseCleanup databaseCleanup;
    @Autowired
    OrderReadService orderReadService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    Owner owner;
    Store store;
    Category category;
    Qrcode qrcode;

    Menu menu1;
    Menu menu2;

    Order order1;
    Order order2;

    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
        owner = ownerRepository.saveAndFlush(Owner.builder()
                .ownerName("정가영")
                .ownerBirth(LocalDateTime.now())
                .ownerEmail("wjdrkdudwkd@email.com")
                .ownerPw("rkdud123!")
                .build()
        );
        store = storeRepository.saveAndFlush(Store.builder()
                .storeName("가게1")
                .owner(owner)
                .build()
        );
        category = categoryRepository.saveAndFlush(
                Category.builder()
                        .store(store)
                        .categoryName(String.valueOf(Math.random()))
                        .categoryDescription(String.valueOf(Math.random()))
                        .build()
        );
        qrcode = qrcodeRepository.saveAndFlush(Qrcode.builder()
                .store(store)
                .qrcodeName(String.valueOf((Math.random()*100)))
                .qrcodeImage("")
                .build());

        menu1 = menuRepository.saveAndFlush(Menu.builder()
                .menuName(String.valueOf(Math.random()))
                .menuDescription(String.valueOf(Math.random()))
                .menuPrice((int) (Math.random()*10000))
                .category(category)
                .store(store)
                .build());

        menu2 = menuRepository.saveAndFlush(Menu.builder()
                .menuName(String.valueOf(Math.random()))
                .menuDescription(String.valueOf(Math.random()))
                .menuPrice((int) (Math.random()*10000))
                .category(category)
                .store(store)
                .build());
        order1 = orderRepository.saveAndFlush(Order.builder()
                        .store(store)
                        .qrcode(qrcode)
                        .orderStatus(OrderStatus.PROGRESS)
                .build());
        order2 = orderRepository.saveAndFlush(Order.builder()
                .store(store)
                .qrcode(qrcode)
                        .orderStatus(OrderStatus.COMPLETE)
                .build());
    }

    public OrderDetail saveOrderDetail1() {
        return orderDetailRepository.saveAndFlush(OrderDetail.builder()
                .order(order1)
                .menu(menu1)
                .orderDetailCount(2)
                .orderMenuPrice(menu1.getMenuPrice())
                .orderDetailTotalPrice(menu1.getMenuPrice() * 2)
                .build());
    }

    public OrderDetail saveOrderDetail2() {
        return orderDetailRepository.saveAndFlush(OrderDetail.builder()
                .order(order1)
                .menu(menu2)
                .orderDetailCount(1)
                .orderMenuPrice(menu2.getMenuPrice())
                .orderDetailTotalPrice(menu2.getMenuPrice())
                .build());
    }

    @Test
    @DisplayName("주문 상세 목록을 조회한다.")
    void getOrderDetails() {

        OrderDetail orderDetail1 = saveOrderDetail1();
        OrderDetail orderDetail2 = saveOrderDetail2();
        Long orderId = orderDetail1.getOrder().getOrderId();
        OrderDetailDto orderDetailDto = OrderDetailDto.builder().orderId(orderId).build();
        Pageable pageable = PageRequest.of(0, 3, Sort.by("orderDetailId").ascending());

        Page<OrderDetailDto> orderDetails = orderReadService.getOrderDetails(orderDetailDto, pageable);

        Assertions.assertThat(orderDetails)
                .hasSize(2)
                .extracting("menuId", "orderDetailCount")
                .contains(Tuple.tuple(orderDetail1.getMenu().getMenuId(), orderDetail1.getOrderDetailCount())
                , Tuple.tuple(orderDetail2.getMenu().getMenuId(), orderDetail2.getOrderDetailCount()));
    }

    @Test
    @DisplayName("모든 상태의 주문 목록을 조회한다.")
    void getOrdersAllStatus() {
        OrderDto orderDto = OrderDto.builder().qrcodeId(qrcode.getQrcodeId()).build();
        Pageable pageable = PageRequest.of(0, 3, Sort.by("orderId").ascending());

        Page<OrderDto> orders = orderReadService.getOrders(orderDto, pageable);

        Assertions.assertThat(orders)
                .hasSize(2)
                .extracting("orderId", "orderStatus")
                .contains(Tuple.tuple(order2.getOrderId(), order2.getOrderStatus()))
                .contains(Tuple.tuple(order1.getOrderId(), order1.getOrderStatus()));
    }

    @Test
    @DisplayName("주문상태에 따른 주문 목록을 조회한다.")
    void getOrdersByStatus() {
        OrderDto orderDto = OrderDto.builder().qrcodeId(qrcode.getQrcodeId()).orderStatus(order2.getOrderStatus()).build();
        Pageable pageable = PageRequest.of(0, 3, Sort.by("orderId").ascending());

        Page<OrderDto> orders = orderReadService.getOrders(orderDto, pageable);

        Assertions.assertThat(orders)
                .hasSize(1)
                .extracting("orderId", "orderStatus")
                .contains(Tuple.tuple(order2.getOrderId(), order2.getOrderStatus()));
    }
}
