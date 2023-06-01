package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {

    private Long id;
    private String name;
    private int age;

    public static MemberResponseDto toResponseDto(Member member) {
        return MemberResponseDto.builder()
                .age(member.getAge())
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
