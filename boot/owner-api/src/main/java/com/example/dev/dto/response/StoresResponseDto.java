package com.example.dev.dto.response;

import com.example.dev.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
public class StoresResponseDto {

    private Long storeId;
    private String storeName;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeStatus;

    public static StoresResponseDto toResponseDto(StoreDto dto) {
        return StoresResponseDto.builder()
                .storeId(dto.getStoreId())
                .storeImage(dto.getStoreImage())
                .storeName(dto.getStoreName())
                .storeOpenTime(dto.getStoreOpenTime())
                .storeCloseTime(dto.getStoreCloseTime())
                .storeStatus(dto.getStoreStatus())
                .build();

    }

}
