package com.example.dev.dto.request;

import com.example.dev.dto.MenuDto;
import com.example.dev.dto.response.MenusResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class MenusRequestDto {

    private Long storeId;
    private Long categoryId;

    public MenuDto toDto() {
        return MenuDto.builder()
                .storeId(storeId)
                .categoryId(categoryId)
                .build();
    }
}
