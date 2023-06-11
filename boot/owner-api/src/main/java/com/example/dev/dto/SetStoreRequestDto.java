package com.example.dev.dto;

import com.example.dev.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class SetStoreRequestDto {

    private Long ownerId;
    private String storeName;
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;

    public StoreDto toDto() {
        return StoreDto.builder()
                .ownerId(ownerId)
                .storeName(storeName)
                .storeDescrition(storeDescrition)
                .storeImage(storeImage)
                .storeOpenTime(storeOpenTime)
                .storeCloseTime(storeCloseTime)
                .build();
    }
}
