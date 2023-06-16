package com.example.dev.dto;

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
    private Pageable pageable;

    @Builder
    public MenuDto(Pageable pageable, Long menuId, Long storeId, Long categoryId, String menuName, Integer menuPrice, String menuImage, String menuStatus, String menuBadge, String menuDescription) {
        this.menuId = menuId;
        this.storeId = storeId;
        this.categoryId = categoryId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
        this.menuStatus = menuStatus;
        this.menuBadge = menuBadge;
        this.menuDescription = menuDescription;
        this.pageable = pageable;
    }

    public Menu toEntity(Store store) {
        return Menu.builder()
                .store(store)
                .categoryId(categoryId)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuImage(menuImage)
                .menuStatus(menuStatus)
                .menuBadge(menuBadge)
                .menuDescription(menuDescription)
                .build();
    }
}
