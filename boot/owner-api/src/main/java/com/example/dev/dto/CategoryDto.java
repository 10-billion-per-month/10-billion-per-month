package com.example.dev.dto;

import com.example.dev.entity.Category;
import com.example.dev.entity.Store;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class CategoryDto {

    private Long categoryId;
    private Long storeId;
    private String categoryName;
    private String categoryDescription;
    private Pageable pageable;

    @Builder
    public CategoryDto(Long categoryId, Long storeId, String categoryName, String categoryDescription, Pageable pageable) {
        this.categoryId = categoryId;
        this.storeId = storeId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.pageable = pageable;
    }

    public Category toEntity(Store store) {
        return Category.builder()
                .store(store)
                .categoryName(categoryName)
                .categoryDescription(categoryDescription)
                .build();
    }

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .storeId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }
}
