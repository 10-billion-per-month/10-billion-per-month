package com.example.dev.service;

import com.example.dev.dto.OwnerDto;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.service.OwnerWriteService;
import com.example.dev.entity.Owner;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OwnerWriteServiceTest {

    @Autowired
    OwnerWriteService ownerWriteService;

    @Autowired
    OwnerRepository ownerRepository;

    //모든 테스트가 시작전
//    @BeforeAll
    // 하나의 테스트 시작 전
    @BeforeEach
    void setUp() {
        ownerRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("아이디가 없을때 Owner를 생성한다.")
    void setOwner() {
        // BDD 방식
        // given : 무엇을 할것인가? 데이터 세팅
        OwnerDto ownerDto = createdOwnerDto();

        // when : 실제 수행
        ownerWriteService.setOwner(ownerDto);

        // then : 수행 결과 확인
        List<Owner> all = ownerRepository.findAll();
        Assertions.assertThat(all)
                .hasSize(1) // 사이즈가 하나있는걸 테스트한다.
                .extracting("ownerName", "ownerEmail")
                .contains(
                        Tuple.tuple("정가영", "wjdrkdudwkd@email.com")
                );
        for (Owner owner : all) {
            System.out.println("owner.getOwnerId() = " + owner.getOwnerId());
            System.out.println("owner.getOwnerPw() = " + owner.getOwnerPw());
            System.out.println("owner.getOwnerStatus() = " + owner.getOwnerStatus());
            System.out.println("owner.getDeleteAt() = " + owner.getDeleteAt());
            System.out.println("owner.getCreatedAt() = " + owner.getCreatedAt());
        }
    }

    @Test
    @DisplayName("아이디가 있으면 예외를 던진다,")
    void setOwnerThrowsException() {
        // BDD 방식
        // given : 무엇을 할것인가? 데이터 세팅
        OwnerDto ownerDto = createdOwnerDto();
        ownerRepository.save(ownerDto.toEntity());

        // when : 실제 수행 & then : 수행 결과 확인
        Assertions.assertThatThrownBy(() -> ownerWriteService.setOwner(ownerDto))
                .isInstanceOf(IllegalStateException.class)
                .message().isEqualTo("이미 있는 아이디입니다.");
    }

    private OwnerDto createdOwnerDto() {
        return OwnerDto.builder()
                .ownerName("정가영")
                .ownerBirth(LocalDateTime.now())
                .ownerEmail("wjdrkdudwkd@email.com")
                .ownerPw("rkdud123!")
                .build();
    }

}