package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class MemberRequestDto {

//    private Long id;
//    private int age;
    private String name;

    public MemberDto toDto(){
        return MemberDto.builder()
//                .id(this.id)
//                .age(this.age)
                .name(this.name)
                .build();
    }
}
