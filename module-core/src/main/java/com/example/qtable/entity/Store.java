package com.example.qtable.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
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
