package com.example.dev.dto.request;

import com.example.dev.dto.MenuDto;
import lombok.Getter;

@Getter
public class DeleteMenuRequestDto {

    private Long menuId;

    public MenuDto toDto() {
        return MenuDto.builder()
                .menuId(menuId)
                .build();
    }
}
