package com.example.dev.dto.request;

import com.example.dev.dto.MenuDto;
import lombok.Getter;

@Getter
public class CreateMenuRequestDto {

    private Long storeId;
    private Long categoryId;
    private String menuName;
    private Integer menuPrice;
    private String menuImage;
    private String menuDescription;

    public MenuDto toDto() {
        return MenuDto.builder()
                .storeId(storeId)
                .categoryId(categoryId)
                .menuName(menuName)
                .menuImage(menuImage)
                .menuDescription(menuDescription)
                .build();
    }
}
