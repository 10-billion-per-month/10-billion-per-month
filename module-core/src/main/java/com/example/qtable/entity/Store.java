package com.example.qtable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    private Long ownerId;
    private String storeName;
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeStatus;

    @Builder
    public Store(Long ownerId, String storeName, String storeDescrition, String storeImage, LocalTime storeOpenTime, LocalTime storeCloseTime, String storeStatus) {
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeDescrition = storeDescrition;
        this.storeImage = storeImage;
        this.storeOpenTime = storeOpenTime;
        this.storeCloseTime = storeCloseTime;
        this.storeStatus = storeStatus;
    }
}
