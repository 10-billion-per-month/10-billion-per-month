package com.example.dev.service;

import com.example.dev.dto.OrderDetailDto;
import com.example.dev.entity.*;
import com.example.dev.repository.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.checkerframework.checker.units.qual.A;
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
import java.util.ArrayList;
import java.util.List;

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

    Order order;

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
                .menuPrice(Integer.valueOf((int) (Math.random()*10000)))
                .category(category)
                .store(store)
                .build());

        menu2 = menuRepository.saveAndFlush(Menu.builder()
                .menuName(String.valueOf(Math.random()))
                .menuDescription(String.valueOf(Math.random()))
                .menuPrice(Integer.valueOf((int) (Math.random()*10000)))
                .category(category)
                .store(store)
                .build());
        order = orderRepository.saveAndFlush(Order.builder()
                        .store(store)
                        .qrcode(qrcode)
                .build());
    }

    public OrderDetail saveOrderDetail1() {
        return orderDetailRepository.saveAndFlush(OrderDetail.builder()
                .order(order)
                .menu(menu1)
                .orderDetailCount(2)
                .orderMenuPrice(menu1.getMenuPrice())
                .orderDetailTotalPrice(menu1.getMenuPrice() * 2)
                .build());
    }

    public OrderDetail saveOrderDetail2() {
        return orderDetailRepository.saveAndFlush(OrderDetail.builder()
                .order(order)
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
}
