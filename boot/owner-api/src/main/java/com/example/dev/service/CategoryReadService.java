package com.example.dev.service;

import com.example.dev.dto.CategoryDto;
import com.example.dev.dto.StoreDto;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.CategoryRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryReadService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;


    /**
     * 카테고리 목록 조회
     * @param dto
     * @return
     */
    public Page<CategoryDto> getCategorys(CategoryDto dto) {
        storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 가게님입니다.", dto.getStoreId())));
        return categoryRepository.findAllByStore_StoreId(dto.getStoreId(), dto.getPageable()).map(CategoryDto::toDto);
    }
}
