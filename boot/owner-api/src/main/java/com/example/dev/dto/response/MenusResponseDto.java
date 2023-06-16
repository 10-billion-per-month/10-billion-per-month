package com.example.dev.dto.response;

import com.example.dev.dto.MenuDto;
import com.example.dev.entity.Store;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MenusResponseDto {

    private Long menuId;
    private Long storeId;
    private Long categoryId;
    private String menuName;
    private Integer menuPrice;
    private String menuImage;
    private String menuStatus;
    private String menuBadge;
    private String menuDescription;

    public static MenusResponseDto toResponseDto(MenuDto dto) {
        return MenusResponseDto.builder()
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
