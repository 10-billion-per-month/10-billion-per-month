package com.example.dev.dto.request;

import com.example.dev.dto.OwnerDto;
import lombok.Getter;

@Getter
public class DuplicateCheckOwnerEmailRequestDto {

    private String ownerEmail;

    public OwnerDto toDto() {
        return OwnerDto.builder().ownerEmail(ownerEmail).build();
    }
}
