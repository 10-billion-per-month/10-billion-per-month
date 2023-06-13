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
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeStatus;

    public static StoresResponseDto toResponseDto(StoreDto dto) {
        return StoresResponseDto.builder()
                .storeId(dto.getStoreId())
                .storeName(dto.getStoreName())
                .storeDescrition(dto.getStoreDescrition())
                .storeOpenTime(dto.getStoreOpenTime())
                .storeCloseTime(dto.getStoreCloseTime())
                .storeStatus(dto.getStoreStatus())
                .build();

    }

}
