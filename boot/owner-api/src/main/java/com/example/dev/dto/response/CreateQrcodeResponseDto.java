package com.example.dev.dto.response;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.entity.Qrcode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateQrcodeResponseDto {

    private Long qrcodeId;
    private String qrcodeImage;
    private String qrcodeName;

    public static CreateQrcodeResponseDto from(QrcodeDto dto) {
        return CreateQrcodeResponseDto.builder()
                .qrcodeId(dto.getQrcodeId())
                .qrcodeImage(dto.getQrcodeImage())
                .qrcodeName(dto.getQrcodeName())
                .build();
    }

}
