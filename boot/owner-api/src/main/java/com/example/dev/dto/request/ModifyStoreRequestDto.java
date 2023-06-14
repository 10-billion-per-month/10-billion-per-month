package com.example.dev.dto.request;

import com.example.dev.dto.StoreDto;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class ModifyStoreRequestDto {

    private Long storeId;
    private String storeName;
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;

    public StoreDto toDto() {
        return StoreDto.builder()
                .storeId(storeId)
                .storeName(storeName)
                .storeDescrition(storeDescrition)
                .storeImage(storeImage)
                .storeOpenTime(storeOpenTime)
                .storeCloseTime(storeCloseTime)
                .build();
    }
}
