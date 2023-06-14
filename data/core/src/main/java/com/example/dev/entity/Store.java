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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private String storeName;
    private String storeDescrition;
    private String storeImage;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeStatus;

    @Builder
    public Store(String deleteAt, Owner owner, String storeName, String storeDescrition, String storeImage, LocalTime storeOpenTime, LocalTime storeCloseTime, String storeStatus) {
        this.owner = owner;
        this.storeName = storeName;
        this.storeDescrition = storeDescrition;
        this.storeImage = storeImage;
        this.storeOpenTime = storeOpenTime;
        this.storeCloseTime = storeCloseTime;
        this.storeStatus = storeStatus;
        super.deleteAt = deleteAt;
    }

    // 수정
    public void modifyStore(Store store, boolean nullAble) {
        if (nullAble) {
            this.owner = store.getOwner();
            this.storeName = store.getStoreName();
            this.storeDescrition = store.getStoreDescrition();
            this.storeImage = store.getStoreImage();
            this.storeOpenTime = store.getStoreOpenTime();
            this.storeCloseTime = store.getStoreCloseTime();
            this.storeStatus = store.getStoreStatus();
        } else {
            this.storeName = store.getStoreName() != null ? store.getStoreName() : this.storeName;
            this.storeDescrition = store.getStoreDescrition() != null ? store.getStoreDescrition() : this.storeDescrition;
            this.storeImage = store.getStoreImage() != null ? store.getStoreImage() : this.storeImage;
            this.storeOpenTime = store.getStoreOpenTime() != null ? store.getStoreOpenTime() : this.storeOpenTime;
            this.storeCloseTime = store.getStoreCloseTime() != null ? store.getStoreCloseTime() : this.storeCloseTime;
            this.storeStatus = store.getStoreStatus() != null ? store.getStoreStatus() : this.storeStatus;
            super.deleteAt = store.getDeleteAt() != null ? store.getDeleteAt() : super.deleteAt;
        }
    }
}
