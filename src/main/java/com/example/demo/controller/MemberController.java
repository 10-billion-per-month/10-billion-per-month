package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/member")
    public void delMember(MemberDeleteRequestDto requestDto) {
        memberService.delMember(requestDto.toDto());
    }
}
