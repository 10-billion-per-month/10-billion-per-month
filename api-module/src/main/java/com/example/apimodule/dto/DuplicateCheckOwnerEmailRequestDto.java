package com.example.apimodule.dto;

import lombok.Getter;

@Getter
public class DuplicateCheckOwnerEmailRequestDto {

    private String ownerEmail;

    public OwnerDto toDto() {
        return OwnerDto.builder().ownerEmail(ownerEmail).build();
    }
}
