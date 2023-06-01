package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class MemberEditRequestDto {

    private Long id;
    private String name;
    private int age;

    public MemberDto toDto(){
        return MemberDto.builder()
                .id(this.id)
                .age(this.age)
                .name(this.name)
                .build();
    }
}
