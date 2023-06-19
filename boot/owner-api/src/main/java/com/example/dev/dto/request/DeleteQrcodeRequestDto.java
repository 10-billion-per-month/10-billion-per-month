package com.example.dev.dto.request;

import com.example.dev.dto.QrcodeDto;
import lombok.Getter;

@Getter
public class DeleteQrcodeRequestDto {

    private Long qrcodeId;

    public QrcodeDto toDto() {
        return QrcodeDto.builder()
                .qrcodeId(qrcodeId)
                .build();
    }
}
