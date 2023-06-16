package com.example.dev.service;

import com.example.dev.dto.CategoryDto;
import com.example.dev.entity.Category;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import com.example.dev.repository.CategoryRepository;
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

@SpringBootTest
public class CategoryReadServiceTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryReadService categoryReadService;

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    DatabaseCleanup databaseCleanup;

    Owner owner;
    Store store;

    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
        owner = ownerRepository.saveAndFlush(Owner.builder()
                .ownerName("정가영")
                .ownerBirth(LocalDateTime.now())
                .ownerEmail("wjdrkdudwkd@email.com")
                .ownerPw("rkdud123!")
                .build());
        store = storeRepository.saveAndFlush(Store.builder()
                .storeName("가게1")
                .owner(owner)
                .build()
        );
    }

    private Category createCategoryEntity() {
        return categoryRepository.saveAndFlush(Category.builder()
                .store(store)
                .categoryName(String.valueOf(Math.random()))
                .categoryDescription(String.valueOf(Math.random()))
                .build());
    }

    @Test
    @DisplayName("가게의 카테고리 목록을 조회한다.")
    void getCategorys() {
        // given : 무엇을 할것인가? 데이터 세팅
        Category categoryEntity1 = createCategoryEntity();
        Category categoryEntity2 = createCategoryEntity();
        Category categoryEntity3 = createCategoryEntity();
        Category categoryEntity4 = createCategoryEntity();

        // when : 실제 수행
        Pageable pageable = PageRequest.of(0, 3, Sort.by("categoryId").descending());

        CategoryDto categoryDto = CategoryDto.builder()
                .storeId(store.getStoreId())
                .pageable(pageable)
                .build();
        Page<CategoryDto> categorys = categoryReadService.getCategorys(categoryDto);

        // then : 수행 결과 확인
        Assertions.assertThat(categorys)
                .hasSize(3)
                .extracting("categoryName", "categoryDescription")
                .contains(Tuple.tuple(categoryEntity4.getCategoryName(), categoryEntity4.getCategoryDescription())
                        , Tuple.tuple(categoryEntity3.getCategoryName(), categoryEntity3.getCategoryDescription())
                        , Tuple.tuple(categoryEntity2.getCategoryName(), categoryEntity2.getCategoryDescription())
                );

        // 페이징 데이터 확인
        Assertions.assertThat(categorys.getTotalElements())
                .isEqualTo(4);
        Assertions.assertThat(categorys.getTotalPages())
                .isEqualTo(2);
    }
}
