package com.example.dev.dto;

import com.example.dev.entity.Owner;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OwnerDto {

    private Long ownerId;
    private String ownerEmail;
    private String ownerPw;
    private String ownerName;
    private LocalDateTime ownerBirth;
    private String ownerStatus;

    @Builder
    public OwnerDto(Long ownerId, String ownerEmail, String ownerPw, String ownerName, LocalDateTime ownerBirth, String ownerStatus) {
        this.ownerId = ownerId;
        this.ownerEmail = ownerEmail;
        this.ownerPw = ownerPw;
        this.ownerName = ownerName;
        this.ownerBirth = ownerBirth;
        this.ownerStatus = ownerStatus;
    }

    public Owner toEntity() {
        return Owner.builder()
                .ownerEmail(ownerEmail)
                .ownerPw(ownerPw)
                .ownerName(ownerName)
                .ownerBirth(ownerBirth)
                .ownerStatus(ownerStatus)
                .build();
    }


}


