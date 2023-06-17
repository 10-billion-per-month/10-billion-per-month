package com.example.dev.service;

import com.example.dev.dto.CategoryDto;
import com.example.dev.entity.Category;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.CategoryRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryWriteService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    /**
     * 카테고리 등록
     * @param dto
     */
    public Category createCategory(CategoryDto dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 가게입니다.", dto.getStoreId())));
        return categoryRepository.save(dto.toEntity(store));
    }

    /**
     * 카테고리 수정
     * @param dto
     */
    public void modifyCategory(CategoryDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 가게입니다.  categoryId = s%", dto.getCategoryId())));
        category.modifyCategory(Category.builder()
                        .categoryName(dto.getCategoryName())
                        .categoryDescription(dto.getCategoryDescription())
                .build(), false);
    }

    /**
     * 카테고리 삭제
     */
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 카테고리 입니다.", categoryId)));
        category.modifyCategory(Category.builder().deleteAt("Y")
                .build(), false);
    }
}
