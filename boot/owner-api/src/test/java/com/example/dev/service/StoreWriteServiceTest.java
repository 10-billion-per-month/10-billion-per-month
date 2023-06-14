package com.example.dev.service;

import com.example.dev.dto.StoreDto;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StoreWriteServiceTest {

    @Autowired
    StoreWriteService storeWriteService;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OwnerRepository ownerRepository;

    // 하나의 테스트 시작 전
    @BeforeEach
    void setUp() {
        ownerRepository.deleteAllInBatch();
    }

    private Store createdStore(Owner owner, StoreDto storeDto) {
        return storeRepository.save(storeDto.toEntity(owner));
    }

    private StoreDto createdStoreDto(Long ownerId) {
        return StoreDto.builder()
                .ownerId(ownerId)
                .storeImage("url")
                .storeDescrition("가게 설명 입니다.")
                .storeOpenTime(LocalTime.now())
                .storeCloseTime(LocalTime.now())
                .storeName("1번 가게")
                .build();
    }

    private Owner createdOwnerEntity() {
        return ownerRepository.saveAndFlush(Owner.builder()
                .ownerName("정가영")
                .ownerBirth(LocalDateTime.now())
                .ownerEmail("wjdrkdudwkd@email.com")
                .ownerPw("rkdud123!")
                .build());
    }

    @Test
    @DisplayName("사장님이 존재할 때 가게를 등록 한다.")
    void setStore() {

        // given : 무엇을 할것인가? 데이터 세팅
        Owner owner = createdOwnerEntity();
        StoreDto storeDto = createdStoreDto(owner.getOwnerId());

        // when : 실제 수행
        storeWriteService.createStore(storeDto);

        // then : 수행 결과 확인
        List<Store> storeList = storeRepository.findAllByOwner(owner);
        Assertions.assertThat(storeList)
                .hasSize(1)
                .extracting("storeName", "storeImage")
                .contains(Tuple.tuple("1번 가게", "url"));
    }

    @Test
    @DisplayName("사장님이 존재하지 않을 때 가게를 등록 한다.")
    void setStoreThen() {

        // given : 무엇을 할것인가? 데이터 세팅
        StoreDto storeDto = createdStoreDto(9999L);

        // when : 실제 수행 & then : 수행 결과 확인
        Assertions.assertThatThrownBy(() -> storeWriteService.createStore(storeDto))
                .isInstanceOf(CommonException.class);
    }

    @Test
    @DisplayName("가게 정보를 수정 한다.")
    void modifyStore() {

        // given : 무엇을 할것인가? 데이터 세팅
        Owner owner = createdOwnerEntity();
        StoreDto storeDto = createdStoreDto(owner.getOwnerId());
        Store store = createdStore(owner, storeDto);

        StoreDto 가게_이름_변경 = StoreDto.builder()
                .storeId(store.getStoreId())
                .storeName("가게 이름 변경")
                .build();

        // when : 실제 수행
        storeWriteService.modifyStore(가게_이름_변경);

        // then : 수행 결과 확인
        Store changeStore = storeRepository.findById(store.getStoreId()).get();
        Assertions.assertThat(changeStore.getStoreName())
                .isEqualTo("가게 이름 변경");
        Assertions.assertThat(changeStore.getStoreImage())
                .isEqualTo(store.getStoreImage());
    }


}
