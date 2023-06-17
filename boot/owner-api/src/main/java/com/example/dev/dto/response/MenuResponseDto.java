package com.example.dev.dto.response;

import com.example.dev.dto.MenuDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MenuResponseDto {

    private Long menuId;
    private Long storeId;
    private Long categoryId;
    private String menuName;
    private Integer menuPrice;
    private String menuImage;
    private String menuStatus;
    private String menuBadge;
    private String menuDescription;

    public static MenuResponseDto from(MenuDto dto) {
        return MenuResponseDto.builder()
                .menuId(dto.getMenuId())
                .storeId(dto.getStoreId())
                .categoryId(dto.getCategoryId())
                .menuName(dto.getMenuName())
                .menuPrice(dto.getMenuPrice())
                .menuImage(dto.getMenuImage())
                .menuStatus(dto.getMenuStatus())
                .menuBadge(dto.getMenuBadge())
                .menuDescription(dto.getMenuDescription())
                .build();
    }
}
