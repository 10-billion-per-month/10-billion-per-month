package com.example.dev.dto;

import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

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
    private Pageable pageable;

    @Builder
    public StoreDto(Long storeId, Long ownerId, String storeName, String storeDescrition, String storeImage, LocalTime storeOpenTime, LocalTime storeCloseTime, String storeStatus, Pageable pageable) {
        this.storeId = storeId;
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeDescrition = storeDescrition;
        this.storeImage = storeImage;
        this.storeOpenTime = storeOpenTime;
        this.storeCloseTime = storeCloseTime;
        this.storeStatus = storeStatus;
        this.pageable = pageable;
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

    public static StoreDto toDto(Store store) {
        return StoreDto.builder()
                .ownerId(store.getOwner().getOwnerId())
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .storeDescrition(store.getStoreDescrition())
                .storeImage(store.getStoreImage())
                .storeOpenTime(store.getStoreOpenTime())
                .storeCloseTime(store.getStoreCloseTime())
                .storeStatus(store.getStoreStatus())
                .build();
    }
}
