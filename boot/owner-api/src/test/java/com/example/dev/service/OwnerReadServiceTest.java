package com.example.dev.service;

import com.example.dev.dto.OwnerDto;
import com.example.dev.repository.CategoryRepository;
import com.example.dev.repository.MenuRepository;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
import com.example.dev.service.OwnerReadService;
import jdk.jfr.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OwnerReadServiceTest {

    @Autowired
    OwnerReadService ownerReadService;
    @Autowired
    OwnerWriteService ownerWriteService;

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    DatabaseCleanup databaseCleanup;


    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
    }

    /**
     * 정규식 통과 이메일
     * @return
     */
    private OwnerDto createOwnerDto() {
        return OwnerDto.builder().ownerEmail("wjdrkdudWkd@email.com").ownerPw("비밀임").build();
    }

    /**
     * 정규식 미통과 이메일
     * @return
     */
    private OwnerDto createFailEmailOwnerDto() {
        return OwnerDto.builder()
                .ownerEmail("wjdrkdud")
                .ownerPw("비밀임")
                .build();
    }

    @DisplayName("사장님 아이디가 정규식을 통과하고 중복이 아니면 false 를 반환한다.")
    @Test
    void duplicateCheckOwnerEmail() {
        // given : 무엇을 할것인가? 데이터 세팅
        OwnerDto dto = createOwnerDto();

        // when : 실제 수행
        boolean result = ownerReadService.duplicateCheckOwnerEmail(dto);

        // then : 수행 결과 확인
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("사장님 아이디가 정규식을 통과하고 중복이면 true 를 반환한다.")
    @Test
    void duplicateCheckOwnerEmailThenReturn1() {

        // given : 무엇을 할것인가? 데이터 세팅
        OwnerDto dto = createOwnerDto();
        ownerRepository.save(dto.toEntity());

        // when : 실제 수행
        boolean result = ownerReadService.duplicateCheckOwnerEmail(dto);

        // then : 수행 결과 확인
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("사장님 아이디가 정규식을 통과하지 못하면 예외")
    @Test
    void duplicateCheckOwnerEmailThenfailEmailFormat() {
        // given : 무엇을 할것인가? 데이터 세팅
        OwnerDto failEmailDto = createFailEmailOwnerDto();

        // when : 실제 수행 & then : 수행 결과 확인
        Assertions.assertThatThrownBy(() -> ownerReadService.duplicateCheckOwnerEmail(failEmailDto))
                .isInstanceOf(IllegalArgumentException.class)
                .message().isEqualTo("이메일 형식이 아닙니다.");
    }
    
    @Test
    void login() {
        // given
        OwnerDto ownerDto = createOwnerDto();
        ownerWriteService.createOwner(ownerDto);

        // when 
        String accessToken = ownerReadService.login(ownerDto);

        // then
        Assertions.assertThat(accessToken)
                .isNotBlank();
        System.out.println("accessToken = " + accessToken);
    }




}