package com.example.dev.dto.response;

import com.example.dev.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CategorysResponseDto {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    public static CategorysResponseDto toResponseDto(CategoryDto categoryDto) {
        return CategorysResponseDto.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryName(categoryDto.getCategoryName())
                .categoryDescription(categoryDto.getCategoryDescription())
                .build();
    }
}
