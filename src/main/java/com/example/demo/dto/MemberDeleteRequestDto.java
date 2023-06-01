package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class MemberDeleteRequestDto {

    private Long id;

    public MemberDto toDto(){
        return MemberDto.builder()
                .id(this.id)
                .build();
    }
}
