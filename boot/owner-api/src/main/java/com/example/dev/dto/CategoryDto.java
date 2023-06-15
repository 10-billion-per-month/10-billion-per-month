package com.example.dev.dto;

import com.example.dev.entity.Category;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryDto {

    private Long categoryId;
    private Long storeId;
    private String categoryName;
    private String categoryDescription;

    @Builder
    public CategoryDto(Long categoryId, Long storeId, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.storeId = storeId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Category toEntity(Store store) {
        return Category.builder()
                .store(store)
                .categoryName(categoryName)
                .categoryDescription(categoryDescription)
                .build();
    }
}
