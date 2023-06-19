package com.example.dev.dto.request;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.dto.QrcodeImageConfig;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ModifyQrcodeRequestDto {

    @NotEmpty
    private String qrcodeName;
    private Integer qrImageOnColor;
    private Integer qrImageOffColor;
    @Min(1)
    private Integer qrImageSize;

    public QrcodeDto toDto() {
        return QrcodeDto.builder()
                .qrcodeName(qrcodeName)
                .qrcodeImageConfig(QrcodeImageConfig.builder()
                        .size(qrImageSize)
                        .onColor(qrImageOnColor)
                        .offColor(qrImageOffColor)
                        .build())
                .build();
    }
}
