package com.example.dev.dto.request;

import com.example.dev.dto.CategoryDto;
import lombok.Getter;

@Getter
public class ModifyCategoryRequestDto {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    public CategoryDto toDto() {
        return CategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryDescription(categoryDescription)
                .build();
    }
}
