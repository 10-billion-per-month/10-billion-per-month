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
import java.util.Random;

@SpringBootTest
public class MenuWriteServiceTest {

    @Autowired
    MenuWriteService menuWriteService;
    @Autowired
    MenuReadService menuReadService;
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
    @DisplayName("메뉴 목록을 조회한다")
    void getMenus() {
        // given : 무엇을 할것인가? 데이터 세팅

        Menu menu1 = saveMenu(category1);
        Menu menu2 = saveMenu(category1);
        Menu menu3 = saveMenu(category1);
        Menu menu4 = saveMenu(category1);
        Menu menu5 = saveMenu(category1);
        Menu menu6 = saveMenu(category1);
        Menu menu7 = saveMenu(category1);

        Menu menu8 = saveMenu(category2);
        Menu menu9 = saveMenu(category2);
        Menu menu10 = saveMenu(category2);
        Menu menu11 = saveMenu(category2);


        // when : 실제 수행
        Pageable pageable = PageRequest.of(0, 3, Sort.by("menuId").descending());
        Page<MenuDto> menus = menuReadService.getMenus(MenuDto.builder()
                .categoryId(category1.getCategoryId())
                .storeId(store.getStoreId())
                .pageable(pageable)
                .build());

        // then : 수행 결과 확인
        Assertions.assertThat(menus)
                .hasSize(3)
                .extracting("menuName", "menuPrice")
                .contains(Tuple.tuple(menu7.getMenuName(), menu7.getMenuPrice())
                , Tuple.tuple(menu6.getMenuName(), menu6.getMenuPrice())
                , Tuple.tuple(menu5.getMenuName(), menu5.getMenuPrice())
                );
    }





}
