package com.example.qtable.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Qrcode extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qrcodeId;
    private Long storeId;
    private String qrcodeImage;
    private String qrcodeName;

    @Builder
    public Qrcode(Long storeId, String qrcodeImage, String qrcodeName) {
        this.storeId = storeId;
        this.qrcodeImage = qrcodeImage;
        this.qrcodeName = qrcodeName;
    }
}
