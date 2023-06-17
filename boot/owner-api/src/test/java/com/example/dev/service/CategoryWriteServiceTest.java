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

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class CategoryWriteServiceTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryWriteService categoryWriteService;

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

    private CategoryDto createCategoryDto() {
        return CategoryDto.builder()
                .storeId(store.getStoreId())
                .categoryName(String.valueOf(Math.random()))
                .categoryDescription(String.valueOf(Math.random()))
                .build();
    }

    @Test
    @DisplayName("카테고리를 등록한다.")
    void createCategory() {

        // given : 무엇을 할것인가? 데이터 세팅
        CategoryDto categoryDto = createCategoryDto();

        // when : 실제 수행
        categoryWriteService.createCategory(categoryDto);

        // then : 수행 결과 확인
        List<Category> all = categoryRepository.findAll();
        Assertions.assertThat(all)
                .hasSize(1) // 사이즈가 하나있는걸 테스트한다.
                .extracting("categoryName", "categoryDescription")
                .contains(
                        Tuple.tuple(categoryDto.getCategoryName(), categoryDto.getCategoryDescription())
                );
    }

    @Test
    @DisplayName("카테고리 수정")
    void modifyCategory() {
        // given : 무엇을 할것인가? 데이터 세팅
        CategoryDto categoryDto = createCategoryDto();
        Category category = categoryRepository.save(categoryDto.toEntity(store));

        // when : 실제 수행
        categoryWriteService.modifyCategory(CategoryDto.builder()
                        .categoryId(category.getCategoryId())
                        .categoryName("카테고리 이름 변경")
                        .categoryDescription("카테고리 설명")
                .build());

        // then : 수행 결과 확인
        Category changeCategory = categoryRepository.findById(category.getCategoryId()).get();
        Assertions.assertThat(changeCategory.getCategoryName())
                .isEqualTo("카테고리 이름 변경");
        Assertions.assertThat(changeCategory.getCategoryDescription())
                .isEqualTo("카테고리 설명");
    }

    @Test
    @DisplayName("카테고리 삭제")
    void deleteCategory() {
        // given : 무엇을 할것인가? 데이터 세팅
        CategoryDto categoryDto = createCategoryDto();
        Category category = categoryRepository.save(categoryDto.toEntity(store));

        // when : 실제 수행
        categoryWriteService.deleteCategory(category.getCategoryId());

        // then : 수행 결과 확인
        boolean empty = categoryRepository.findById(category.getCategoryId())
                .isEmpty();

        Assertions.assertThat(empty).isTrue();
    }
}
