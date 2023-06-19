package com.example.dev.dto.response;

import com.example.dev.dto.QrcodeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class QrcodesResponseDto {

    private Long qrcodeId;
    private String qrcodeImage;
    private String qrcodeName;

    public static QrcodesResponseDto from(QrcodeDto dto) {
        return QrcodesResponseDto.builder()
                .qrcodeId(dto.getQrcodeId())
                .qrcodeImage(dto.getQrcodeImage())
                .qrcodeName(dto.getQrcodeName())
                .build();
    }
}
