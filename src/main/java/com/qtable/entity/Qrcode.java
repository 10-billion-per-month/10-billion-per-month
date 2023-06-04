package com.qtable.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Qrcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QrcodeId;
    private Long storeId;
    private String qrcodeImage;
    private String qrcodeName;
    private Character deleteAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
