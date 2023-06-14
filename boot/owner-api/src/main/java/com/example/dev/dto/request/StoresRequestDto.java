package com.example.dev.dto.request;

import com.example.dev.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@Builder
public class StoresRequestDto {
    private Long ownerId;
    private Pageable pageable;

    public StoreDto toDto() {
        return StoreDto.builder()
                .ownerId(ownerId)
                .pageable(pageable)
                .build();
    }
}
