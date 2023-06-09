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

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
