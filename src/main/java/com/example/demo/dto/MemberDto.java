package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDto {

    private Long id;
    private String name;
    private int age;

    public Member toEntity() {
        return Member.builder()
                .age(this.age)
                .name(this.name)
                .build();
    }
}
