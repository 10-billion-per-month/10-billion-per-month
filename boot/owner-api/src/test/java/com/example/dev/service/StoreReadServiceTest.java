package com.example.dev.service;

import com.example.dev.dto.StoreDto;
import com.example.dev.dto.response.StoreResponseDto;
import com.example.dev.dto.response.StoresResponseDto;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
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
import java.time.LocalTime;
import java.util.List;

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
        storeRepository.deleteAllInBatch();
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

        Pageable pageable = PageRequest.of(0, 3, Sort.by("storeId").descending());
        StoreDto storeDto = StoreDto.builder().ownerId(owner.getOwnerId()).pageable(pageable).build();

        // when : 실제 수행
        Page<StoreDto> stores = storeReadService.getStores(storeDto);
        
        // then : 수행 결과 확인
        Assertions.assertThat(stores)
                .hasSize(3)
                .extracting("storeName", "storeImage")
                .contains(Tuple.tuple(storeDto6.getStoreName(), storeDto6.getStoreImage())
                , Tuple.tuple(storeDto5.getStoreName(), storeDto5.getStoreImage())
                , Tuple.tuple(storeDto4.getStoreName(), storeDto4.getStoreImage())
                );
        
        // 페이징 데이터 확인
        Assertions.assertThat(stores.getTotalElements())
                .isEqualTo(6);
        Assertions.assertThat(stores.getTotalPages())
                .isEqualTo(2);
        System.out.println("stores.toString() = " + stores.toString());

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

    @Test
    @DisplayName("가게가 존재 할 때 가게 상세 정보 조회")
    void getStore() {
        // given : 무엇을 할것인가? 데이터 세팅
        Owner owner = createdOwnerEntity();
        StoreDto storeDto1 = createdStoreDto(owner.getOwnerId());
        storeRepository.save(storeDto1.toEntity(owner));
        List<Store> allByOwner = storeRepository.findAllByOwner(owner);

        // when : 실제 수행
        StoreDto store = storeReadService.getStore(allByOwner.get(0).getStoreId());

        // then : 수행 결과 확인
        Assertions.assertThat(store.getStoreName())
                .isEqualTo(storeDto1.getStoreName());
        Assertions.assertThat(store.getStoreStatus())
                .isEqualTo(storeDto1.getStoreStatus());
        Assertions.assertThat(store.getStoreImage())
                .isEqualTo(storeDto1.getStoreImage());

    }


    @Test
    @DisplayName("가게가 존재 하지 않을 때 예외 발생")
    void getStoreException() {
        // given : 무엇을 할것인가? 데이터 세팅
        StoreDto storeDto1 = createdStoreDto(1L);

        // when : 실제 수행 &&  then : 수행 결과 확인
        Assertions.assertThatThrownBy(() -> storeReadService.getStore(1L))
                        .isInstanceOf(IllegalArgumentException.class)
                                .message().isEqualTo("존재하지 않는 가게입니다.");
    }
}
