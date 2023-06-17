package com.example.dev.dto;

import com.example.dev.entity.Category;
import com.example.dev.entity.Menu;
import com.example.dev.entity.Store;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class MenuDto {

    private Long menuId;
    private Long storeId;
    private Long categoryId;
    private String menuName;
    private Integer menuPrice;
    private String menuImage;
    private String menuStatus;
    private String menuBadge;
    private String menuDescription;

    @Builder
    public MenuDto(Long menuId, Long storeId, Long categoryId, String menuName, Integer menuPrice, String menuImage, String menuStatus, String menuBadge, String menuDescription) {
        this.menuId = menuId;
        this.storeId = storeId;
        this.categoryId = categoryId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
        this.menuStatus = menuStatus;
        this.menuBadge = menuBadge;
        this.menuDescription = menuDescription;
    }

    public static MenuDto from(Menu menu) {
        return MenuDto.builder()
                .menuId(menu.getMenuId())
                .storeId(menu.getMenuId())
                .categoryId(menu.getCategory().getCategoryId())
                .menuName(menu.getMenuName())
                .menuPrice(menu.getMenuPrice())
                .menuImage(menu.getMenuImage())
                .menuStatus(menu.getMenuStatus())
                .menuBadge(menu.getMenuBadge())
                .menuDescription(menu.getMenuDescription())
                .build();
    }

    public Menu toEntity(Store store, Category category) {
        return Menu.builder()
                .store(store)
                .category(category)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuImage(menuImage)
                .menuStatus(menuStatus)
                .menuBadge(menuBadge)
                .menuDescription(menuDescription)
                .build();
    }
}
