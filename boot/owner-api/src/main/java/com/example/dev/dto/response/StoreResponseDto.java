package com.example.dev.dto.response;

import com.example.dev.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class StoreResponseDto {
    private String storeName;
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeStatus;

    public static StoreResponseDto toResponseDto(StoreDto dto) {
        return StoreResponseDto.builder()
                .storeName(dto.getStoreName())
                .storeDescrition(dto.getStoreDescrition())
                .storeOpenTime(dto.getStoreOpenTime())
                .storeCloseTime(dto.getStoreCloseTime())
                .storeStatus(dto.getStoreStatus())
                .storeImage(dto.getStoreImage())
                .build();

    }
}
