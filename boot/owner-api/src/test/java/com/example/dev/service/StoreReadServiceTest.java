package com.example.dev.service;

import com.example.dev.dto.StoreDto;
import com.example.dev.dto.response.StoresResponseDto;
import com.example.dev.entity.Owner;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class StoreReadServiceTest {

    @Autowired
    StoreReadService storeReadService;

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    StoreRepository storeRepository;

    // 하나의 테스트 시작 전
    @BeforeEach
    void setUp() {
        ownerRepository.deleteAllInBatch();
    }

    private StoreDto createdStoreDto(Long ownerId) {
        return StoreDto.builder()
                .ownerId(ownerId)
                .storeImage(String.valueOf(Math.random()))
                .storeDescrition(String.valueOf(Math.random()))
                .storeOpenTime(LocalTime.now())
                .storeCloseTime(LocalTime.now())
                .storeName(String.valueOf(Math.random()))
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
    @DisplayName("사장님의 가게 목록을 조회한다.")
    void getStores() {

        // given : 무엇을 할것인가? 데이터 세팅
        Owner owner = createdOwnerEntity();
        StoreDto storeDto1 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto2 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto3 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto4 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto5 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto6 = createdStoreDto(owner.getOwnerId());

        storeRepository.save(storeDto1.toEntity(owner));
        storeRepository.save(storeDto2.toEntity(owner));
        storeRepository.save(storeDto3.toEntity(owner));
        storeRepository.save(storeDto4.toEntity(owner));
        storeRepository.save(storeDto5.toEntity(owner));
        storeRepository.save(storeDto6.toEntity(owner));

        StoreDto storeDto = StoreDto.builder().ownerId(owner.getOwnerId()).build();
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("storeId").descending());

        // when : 실제 수행
        List<StoresResponseDto> stores = storeReadService.getStores(storeDto, pageRequest);

        // then : 수행 결과 확인
        Assertions.assertThat(stores)
                .hasSize(3)
                .extracting("storeName", "storeDescrition")
                .contains(Tuple.tuple(storeDto6.getStoreName(), storeDto6.getStoreDescrition())
                , Tuple.tuple(storeDto5.getStoreName(), storeDto5.getStoreDescrition())
                , Tuple.tuple(storeDto4.getStoreName(), storeDto4.getStoreDescrition())
                );
    }


    @Test
    @DisplayName("사장님의 가게 총 개수를 조회 한다.")
    void getStoreCount() {

        // given : 무엇을 할것인가? 데이터 세팅
        Owner owner = createdOwnerEntity();
        StoreDto storeDto1 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto2 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto3 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto4 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto5 = createdStoreDto(owner.getOwnerId());
        StoreDto storeDto6 = createdStoreDto(owner.getOwnerId());

        storeRepository.save(storeDto1.toEntity(owner));
        storeRepository.save(storeDto2.toEntity(owner));
        storeRepository.save(storeDto3.toEntity(owner));
        storeRepository.save(storeDto4.toEntity(owner));
        storeRepository.save(storeDto5.toEntity(owner));
        storeRepository.save(storeDto6.toEntity(owner));

        // when : 실제 수행
        Integer storeCount= storeReadService.getStoreCount(owner.getOwnerId());

        // then : 수행 결과 확인
        Assertions.assertThat(storeCount)
                .isEqualTo(6);
    }
}
