package com.example.dev.service;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.dto.QrcodeImageConfig;
import com.example.dev.dto.request.QrcodesRequestDto;
import com.example.dev.dto.response.QrcodesResponseDto;
import com.example.dev.entity.Category;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Qrcode;
import com.example.dev.entity.Store;
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
public class QrcodeReadServiceTest {

    @Autowired
    QrcodeReadService qrcodeReadService;
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

    Owner owner;
    Store store;
    Category category1;
    Category category2;

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
        category1 = categoryRepository.saveAndFlush(
                Category.builder()
                        .store(store)
                        .categoryName(String.valueOf(Math.random()))
                        .categoryDescription(String.valueOf(Math.random()))
                        .build()
        );
        category2 = categoryRepository.saveAndFlush(
                Category.builder()
                        .store(store)
                        .categoryName(String.valueOf(Math.random()))
                        .categoryDescription(String.valueOf(Math.random()))
                        .build()
        );
    }

    public QrcodeDto createQrcodeDto() {
        return QrcodeDto.builder()
                .storeId(store.getStoreId())
                .qrcodeName(String.valueOf(Math.random()*100))
                .qrcodeImageConfig(QrcodeImageConfig.builder()
                        .size(300)
                        .onColor(0xffed8878)
                        .offColor(0xff111111)
                        .extension("png")
                        .build())
                .build();
    }

    public Qrcode saveQrcode(QrcodeDto dto) {
        return qrcodeRepository.saveAndFlush(dto.toEntity(store));
    }

    @Test
    @DisplayName("가게의 큐알코드 목록 조회")
    void getQrcodes() {
        // given : 무엇을 할것인가? 데이터 세팅
        Qrcode qrcode1 = saveQrcode(createQrcodeDto());
        Qrcode qrcode2 = saveQrcode(createQrcodeDto());
        Qrcode qrcode3 = saveQrcode(createQrcodeDto());
        Qrcode qrcode4 = saveQrcode(createQrcodeDto());
        Qrcode qrcode5 = saveQrcode(createQrcodeDto());

        // when : 실제 수행
        Pageable pageable = PageRequest.of(0, 3, Sort.by("qrcodeId").ascending());
        Page<QrcodeDto> qrcodes = qrcodeReadService.getQrcodes(QrcodeDto.builder().storeId(store.getStoreId()).build(), pageable);

        // then : 수행 결과 확인
        Assertions.assertThat(qrcodes)
                .hasSize(3)
                .extracting("qrcodeId", "qrcodeName")
                .contains(Tuple.tuple(qrcode1.getQrcodeId(), qrcode1.getQrcodeName())
                , Tuple.tuple(qrcode2.getQrcodeId(), qrcode2.getQrcodeName()));
    }

}
