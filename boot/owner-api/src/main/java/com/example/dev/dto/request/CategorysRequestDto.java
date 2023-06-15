package com.example.dev.dto.request;

import com.example.dev.dto.CategoryDto;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class CategorysRequestDto {

    private Long storeId;
    private Pageable pageable;

    public CategoryDto toDto() {
        return CategoryDto.builder()
                .storeId(storeId)
                .pageable(pageable)
                .build();
    }
}
