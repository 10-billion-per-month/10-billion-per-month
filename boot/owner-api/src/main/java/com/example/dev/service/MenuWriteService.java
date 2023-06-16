package com.example.dev.service;

import com.example.dev.dto.MenuDto;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.MenuRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MenuWriteService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;


    /**
     * 메뉴 등록
     * @param dto
     */
    public void createMenu(MenuDto dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 가게입니다. storeId = %s", dto.getStoreId())));
        menuRepository.saveAndFlush(dto.toEntity(store));
    }
}
