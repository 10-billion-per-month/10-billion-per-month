package com.example.dev.controller;

import com.example.dev.dto.request.CreateStoreRequestDto;
import com.example.dev.dto.request.ModifyStoreRequestDto;
import com.example.dev.dto.request.StoresRequestDto;
import com.example.dev.dto.response.StoreResponseDto;
import com.example.dev.dto.response.StoresResponseDto;
import com.example.dev.service.StoreReadService;
import com.example.dev.service.StoreWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public void createStore(@RequestBody CreateStoreRequestDto requestDto) {
        storeWriteService.createStore(requestDto.toDto());
    }


    /**
     * 가게 목록 조회
     * @param requestDto
     * @return
     */
    @GetMapping("/v1/stores")
    public Page<StoresResponseDto> getStores(@PageableDefault(size = 10, sort = "storeId", direction = Sort.Direction.ASC) StoresRequestDto requestDto) {
        return storeReadService.getStores(requestDto.toDto()).map(StoresResponseDto::toResponseDto);
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
        return StoreResponseDto.toResponseDto(storeReadService.getStore(storeId));
    }

    /**
     * 가게 수정
     * @param requestDto
     */
    @PutMapping("/v1/store")
    public void modifyStore(@RequestBody ModifyStoreRequestDto requestDto) {
        storeWriteService.modifyStore(requestDto.toDto());
    }

    /**
     * 가게 삭제
     * @param storeId
     */
    @DeleteMapping("/v1/store")
    public void deleteStore(@RequestBody Long storeId) {
        storeWriteService.deleteStore(storeId);
    }


}
