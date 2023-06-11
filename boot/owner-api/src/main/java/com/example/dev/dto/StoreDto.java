package com.example.dev.dto;

import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreDto {

    private Long storeId;
    private Long ownerId;
    private String storeName;
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeStatus;


    @Builder
    public StoreDto(Long ownerId, String storeName, String storeDescrition, String storeImage, LocalTime storeOpenTime, LocalTime storeCloseTime, String storeStatus) {
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeDescrition = storeDescrition;
        this.storeImage = storeImage;
        this.storeOpenTime = storeOpenTime;
        this.storeCloseTime = storeCloseTime;
        this.storeStatus = storeStatus;
    }

    public Store toEntity(Owner owner) {
        return Store.builder()
                .owner(owner)
                .storeName(storeName)
                .storeDescrition(storeDescrition)
                .storeOpenTime(storeOpenTime)
                .storeCloseTime(storeCloseTime)
                .storeStatus(storeStatus)
                .storeImage(storeImage)
                .build();
    }
}
