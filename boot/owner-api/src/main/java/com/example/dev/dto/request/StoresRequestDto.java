package com.example.dev.dto.request;

import com.example.dev.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
public class StoresRequestDto {
    private Long ownerId;

    public StoreDto toDto() {
        return StoreDto.builder()
                .ownerId(ownerId)
                .build();
    }
}
