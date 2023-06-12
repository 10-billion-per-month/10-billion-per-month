package com.example.dev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "delete_at = 'N'")
public class Store extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private String storeName;
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeStatus;

    @Builder
    public Store(Owner owner, String storeName, String storeDescrition, String storeImage, LocalTime storeOpenTime, LocalTime storeCloseTime, String storeStatus) {
        this.owner = owner;
        this.storeName = storeName;
        this.storeDescrition = storeDescrition;
        this.storeImage = storeImage;
        this.storeOpenTime = storeOpenTime;
        this.storeCloseTime = storeCloseTime;
        this.storeStatus = storeStatus;
    }
}
