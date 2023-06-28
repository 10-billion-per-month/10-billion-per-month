package com.example.dev.dto.request;

import com.example.dev.dto.OwnerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private String ownerEmail;
    private String ownerPw;

    public OwnerDto toDto() {
        return OwnerDto.builder()
                .ownerEmail(ownerEmail)
                .ownerPw(ownerPw)
                .build();
    }
}
