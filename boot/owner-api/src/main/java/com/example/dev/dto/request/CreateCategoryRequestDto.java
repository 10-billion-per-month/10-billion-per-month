package com.example.dev.dto.request;

import com.example.dev.dto.CategoryDto;
import com.example.dev.dto.StoreDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateCategoryRequestDto {

    private Long storeId;
    private String categoryName;
    private String categoryDescription;

    public CategoryDto toDto() {
        return CategoryDto.builder()
                .storeId(storeId)
                .categoryName(categoryName)
                .categoryDescription(categoryDescription)
                .build();
    }
}
