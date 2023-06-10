package com.example.dev.entity;

import com.example.dev.entity.Category;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CategoryTest {

    @Test
    void createCategory() {
        Category category = Category.builder()
                .categoryName("카테고리1")
                .storeId(1L)
                .categoryDescription("카테고리 1 입니다.")
                .build();

        assertThat(category.getCategoryName()).isEqualTo("카테고리1");

        assertThat(category.getCreatedAt()).isNull();
    }






}