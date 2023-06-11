package com.example.dev.controller;

import com.example.dev.dto.SetStoreRequestDto;
import com.example.dev.service.StoreWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreWriteService storeWriteService;

    /**
     * 가게 등록
     * @param requestDto
     */
    public void setStore(SetStoreRequestDto requestDto) {
        storeWriteService.setStore(requestDto.toDto());
    }
}
