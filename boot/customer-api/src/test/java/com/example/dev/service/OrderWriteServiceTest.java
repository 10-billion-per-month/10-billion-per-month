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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderWriteServiceTest {

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
    OrderWriteService orderWriteService;
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
    }

    @Test
    @DisplayName("주문 상세를 등록한다")
    void createOrderDetail() {

        // given
        List<OrderDetailDto> orderDetailList = new ArrayList<>();
        OrderDetailDto orderDetailDto1 = OrderDetailDto.builder()
                .menuId(menu1.getMenuId())
                .orderDetailCount(2)
                .orderMenuPrice(menu1.getMenuPrice())
                .orderDetailTotalPrice(menu1.getMenuPrice() * 2)
                .build();
        OrderDetailDto orderDetailDto2 = OrderDetailDto.builder()
                .menuId(menu2.getMenuId())
                .orderDetailCount(1)
                .orderMenuPrice(menu2.getMenuPrice())
                .orderDetailTotalPrice(menu2.getMenuPrice())
                .build();
        orderDetailList.add(orderDetailDto1);
        orderDetailList.add(orderDetailDto2);

        OrderDto orderDto = OrderDto.builder().orderDetailList(orderDetailList).qrcodeId(qrcode.getQrcodeId()).build();

        // when
        orderWriteService.createOrderDetail(orderDto);

        // then
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrder_Qrcode_QrcodeId(qrcode.getQrcodeId());
        orderDetails.forEach(orderDetail -> {
            System.out.println("orderDetail.getOrderDetailId() = " + orderDetail.getOrderDetailId());
            System.out.println("orderDetail.getMenu().getMenuId() = " + orderDetail.getMenu().getMenuId());
            System.out.println("orderDetail.getOrderMenuPrice() = " + orderDetail.getOrderMenuPrice());
            System.out.println("orderDetail.getOrderDetailCount() = " + orderDetail.getOrderDetailCount());
            System.out.println("orderDetail.getOrderDetailTotalPrice() = " + orderDetail.getOrderDetailTotalPrice());
        });

        Assertions.assertThat(orderDetails)
                .hasSize(2)
                .extracting("OrderMenuPrice", "OrderDetailCount", "OrderDetailTotalPrice")
                .contains(
                        Tuple.tuple(orderDetailDto1.getOrderMenuPrice(), orderDetailDto1.getOrderDetailCount(), orderDetailDto1.getOrderDetailTotalPrice())
                        , Tuple.tuple(orderDetailDto2.getOrderMenuPrice(), orderDetailDto2.getOrderDetailCount(), orderDetailDto2.getOrderDetailTotalPrice())
                );

    }

    @Test
    @DisplayName("rabbitMQ")
    void sendMessage() {
        orderWriteService.sendMessage();
    }


}
