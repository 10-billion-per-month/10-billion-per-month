package com.example.dev.service;

import com.example.dev.dto.CategoryDto;
import com.example.dev.dto.MenuDto;
import com.example.dev.entity.Category;
import com.example.dev.entity.Menu;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import com.example.dev.repository.CategoryRepository;
import com.example.dev.repository.MenuRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
public class MenuWriteServiceTest {

    @Autowired
    MenuWriteService menuWriteService;
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

    public MenuDto createdMenuDto() {
        return MenuDto.builder()
                .menuName(String.valueOf(Math.random()))
                .menuDescription(String.valueOf(Math.random()))
                .menuPrice(Integer.valueOf((int) Math.random()*100))
                .categoryId(category1.getCategoryId())
                .storeId(store.getStoreId())
                .build();
    }

    public Menu saveMenu(Category category) {
        return menuRepository.saveAndFlush(createdMenuDto().toEntity(store, category));
    }

    @Test
    @DisplayName("메뉴를 생성한다.")
    void createMenu() {

        // given : 무엇을 할것인가? 데이터 세팅
        MenuDto menuDto = createdMenuDto();

        // when : 실제 수행
        menuWriteService.createMenu(menuDto);

        // then : 수행 결과 확인
        List<Menu> all = menuRepository.findAll();
        Assertions.assertThat(all)
                .hasSize(1) // 사이즈가 하나있는걸 테스트한다.
                .extracting("menuName", "menuPrice")
                .contains(
                        Tuple.tuple(menuDto.getMenuName(), menuDto.getMenuPrice())
                );
    }

    @Test
    @DisplayName("메뉴를 수정한다.")
    void modifyMenu() {
        // given : 무엇을 할것인가? 데이터 세팅
        Menu menu = saveMenu(category1);

        // when : 실제 수행
        menuWriteService.modifyMenu(
                MenuDto.builder()
                        .menuId(menu.getMenuId())
                        .categoryId(category2.getCategoryId())
                        .menuName("수정완료!")
                        .build()
        );

         Menu resultMenu = menuRepository.findById(menu.getMenuId()).get();

        // then : 수행 결과 확인
        Assertions.assertThat(resultMenu.getMenuName())
                .isEqualTo("수정완료!");
        Assertions.assertThat(resultMenu.getCategory().getCategoryId())
                .isEqualTo(category2.getCategoryId());
        Assertions.assertThat(resultMenu.getMenuImage())
                .isEqualTo(menu.getMenuImage());
    }

    @Test
    @DisplayName("메뉴를 삭제한다.")
    void deleteMenu() {
        // given : 무엇을 할것인가? 데이터 세팅
        Menu menu = saveMenu(category1);

        // when : 실제 수행
        menuWriteService.deleteMenu(MenuDto.from(menu));

        // then : 수행 결과 확인
        List<Menu> all = menuRepository.findAll();
        Assertions.assertThat(all).hasSize(0);


    }

}
