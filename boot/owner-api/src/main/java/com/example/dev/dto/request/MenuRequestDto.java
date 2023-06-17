package com.example.dev.dto.request;

import com.example.dev.dto.MenuDto;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class MenuRequestDto {

    @Min(1)
    private Long menuId;

    public MenuDto toDto() {
        return MenuDto.builder().menuId(menuId).build();
    }
}
