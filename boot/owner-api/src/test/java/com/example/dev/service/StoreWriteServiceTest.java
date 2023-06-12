package com.example.dev.service;

import ch.qos.logback.classic.model.processor.LogbackClassicDefaultNestedComponentRules;
import com.example.dev.dto.OwnerDto;
import com.example.dev.dto.StoreDto;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class StoreWriteServiceTest {

    @Autowired
    StoreWriteService storeWriteService;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OwnerRepository ownerRepository;

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
        storeWriteService.setStore(storeDto);

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
        Assertions.assertThatThrownBy(() -> storeWriteService.setStore(storeDto))
                .isInstanceOf(NullPointerException.class)
                .message().isEqualTo("등록되지 않은 사장님입니다.");

    }

}
