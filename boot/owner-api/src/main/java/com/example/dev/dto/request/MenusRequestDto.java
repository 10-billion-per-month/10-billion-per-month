package com.example.dev.dto.request;

import com.example.dev.dto.MenuDto;
import com.example.dev.dto.response.MenusResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class MenusRequestDto {

    private Long storeId;
    private Long categoryId;
    private Pageable pageable;

    public MenuDto toDto() {
        return MenuDto.builder()
                .storeId(storeId)
                .categoryId(categoryId)
                .pageable(pageable)
                .build();
    }
}
