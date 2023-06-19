package com.example.dev.dto.request;

import com.example.dev.dto.QrcodeDto;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class QrcodesRequestDto {

    @Min(1)
    private Long storeId;

    public QrcodeDto toDto() {
        return QrcodeDto.builder()
                .storeId(storeId)
                .build();
    }
}
