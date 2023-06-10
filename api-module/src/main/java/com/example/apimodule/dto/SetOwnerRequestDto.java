package com.example.apimodule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SetOwnerRequestDto {

    private String ownerEmail;
    private String ownerPw;
    private String ownerName;
    private LocalDateTime ownerBirth;
    private String ownerStatus;

    public OwnerDto toDto() {
        return OwnerDto.builder()
                .ownerEmail(ownerEmail)
                .ownerPw(ownerPw)
                .ownerBirth(ownerBirth)
                .build();
    }
}
