package com.example.dev.dto.response;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.dto.QrcodeImageConfig;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ModifyQrcodeResposeDto {

    private Long qrcodeId;
    private String qrcodeImage;
    private String qrcodeName;

    public static ModifyQrcodeResposeDto from(QrcodeDto dto) {
        return ModifyQrcodeResposeDto.builder()
                .qrcodeId(dto.getQrcodeId())
                .qrcodeImage(dto.getQrcodeImage())
                .qrcodeName(dto.getQrcodeName())
                .build();
    }
}
