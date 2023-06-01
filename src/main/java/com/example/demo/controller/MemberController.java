package com.example.demo.controller;

import com.example.demo.dto.MemberEditRequestDto;
import com.example.demo.dto.MemberRequestDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.dto.MemberSetRequestDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public void setMember(MemberSetRequestDto requestDto) {
        memberService.setMember(requestDto.toDto());
    }

    @GetMapping("/member")
    public MemberResponseDto getMember(MemberRequestDto requestDto) {
        return memberService.getMember(requestDto.toDto());
    }

    @PutMapping("/member")
    public void editMember(MemberEditRequestDto requestDto) {
        memberService.editMember(requestDto.toDto());
    }
}
