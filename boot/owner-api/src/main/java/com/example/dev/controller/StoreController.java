package com.example.dev.controller;

import com.example.dev.dto.SetStoreRequestDto;
import com.example.dev.dto.request.PageRequestDto;
import com.example.dev.dto.request.StoresRequestDto;
import com.example.dev.dto.response.StoreResponseDto;
import com.example.dev.dto.response.StoresResponseDto;
import com.example.dev.service.StoreReadService;
import com.example.dev.service.StoreWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    /**
     * 가게 목록 조회 2
     * @param pageable
     * @return
     */
    @GetMapping("/v2/stores")
    public List<StoresResponseDto> getStores(@PageableDefault(size=10, sort="storeId", direction = Sort.Direction.ASC) StoresRequestDto pageable) {
        return storeReadService.getStores2(pageable.toDto());
    }

    /**
     * 가게 총 개수 조회
     * @param ownerId
     * @return
     */
    @GetMapping("/v1/storeCount")
    public Integer getStoreCount(long ownerId) {
        return storeReadService.getStoreCount(ownerId);
    }

    /**
     * 가게 상세 조회
     * @param
     * @return
     */
    @GetMapping("/v1/store")
    public StoreResponseDto getStore(long storeId) {
        return storeReadService.getStore(storeId);
    }


}
