package com.example.dev.controller;

import com.example.dev.dto.SetStoreRequestDto;
import com.example.dev.dto.request.PageRequestDto;
import com.example.dev.dto.request.StoresRequestDto;
import com.example.dev.dto.response.StoresResponseDto;
import com.example.dev.service.StoreReadService;
import com.example.dev.service.StoreWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreWriteService storeWriteService;
    private final StoreReadService storeReadService;


    /**
     * 가게 등록
     * @param requestDto
     */
    @PostMapping("/v1/store")
    public void setStore(SetStoreRequestDto requestDto) {
        storeWriteService.setStore(requestDto.toDto());
    }

    /**
     * 가게 목록 조회
     * @param requestDto
     * @param pageRequestDto
     * @return
     */
    @GetMapping("/v1/stores")
    public List<StoresResponseDto> getStores(StoresRequestDto requestDto, PageRequestDto pageRequestDto) {
        return storeReadService.getStores(requestDto.toDto(), pageRequestDto.toPageRequest());
    }

    @GetMapping("/v1/storeCount")
    public Integer getStoreCount(int ownerId) {
        return storeReadService.getStoreCount(ownerId);
    }


}
