package com.example.dev.dto;

import com.example.dev.entity.Qrcode;
import com.example.dev.entity.Store;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
@Builder
public class QrcodeDto {

    private Long qrcodeId;
    private Long storeId;
    private String qrcodeImage;
    private String qrcodeName;
    private QrcodeImageConfig qrcodeImageConfig;

    public Qrcode toEntity(Store store) {
        return Qrcode.builder()
                .qrcodeName(qrcodeName)
                .qrcodeImage(qrcodeImage)
                .store(store)
                .build();
    }

    public Qrcode toEntity(Store store, String qrcodeImage) {
        return Qrcode.builder()
                .qrcodeName(qrcodeName)
                .qrcodeImage(qrcodeImage)
                .store(store)
                .build();
    }

    public static QrcodeDto from(Qrcode qrcode) {
        return QrcodeDto.builder()
                .qrcodeId(qrcode.getQrcodeId())
                .qrcodeName(qrcode.getQrcodeName())
                .qrcodeImage(qrcode.getQrcodeImage())
                .build();
    }

}
