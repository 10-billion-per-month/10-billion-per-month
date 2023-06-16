package com.example.dev.service;

import com.example.dev.dto.MenuDto;
import com.example.dev.entity.Category;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.CategoryRepository;
import com.example.dev.repository.MenuRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuReadService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    /**
     * 가게 메뉴 목록 조회
     * @param dto
     * @return
     */
    public List<MenuDto> getMenus(MenuDto dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 가게님입니다.", dto.getStoreId())));
        Category category = categoryRepository.findByStore_StoreIdAndCategoryId(dto.getStoreId(), dto.getCategoryId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 카테고리입니다.", dto.getStoreId())));

        return null;
    }
}
