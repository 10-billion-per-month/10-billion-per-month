package com.example.dev.dto.request;

import com.example.dev.dto.OwnerDto;
import com.example.dev.dto.validation.email.EmailUnique;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateOwnerRequestDto {

    @EmailUnique
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
