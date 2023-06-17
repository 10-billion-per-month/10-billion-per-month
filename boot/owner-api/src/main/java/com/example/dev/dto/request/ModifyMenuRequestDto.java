package com.example.dev.dto.request;

import com.example.dev.dto.MenuDto;
import lombok.Getter;

@Getter
public class ModifyMenuRequestDto {

    private Long menuId;
    private Long categoryId;
    private String menuName;
    private Integer menuPrice;
    private String menuImage;
    private String menuStatus;
    private String menuBadge;
    private String menuDescription;

    public MenuDto toDto() {
        return MenuDto.builder()
                .menuId(menuId)
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
