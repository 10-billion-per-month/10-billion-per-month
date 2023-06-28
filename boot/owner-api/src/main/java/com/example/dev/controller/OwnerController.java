package com.example.dev.controller;

import com.example.dev.dto.request.DuplicateCheckOwnerEmailRequestDto;
import com.example.dev.dto.request.CreateOwnerRequestDto;
import com.example.dev.dto.request.LoginRequestDto;
import com.example.dev.service.OwnerReadService;
import com.example.dev.service.OwnerWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OwnerController {

    private final OwnerReadService ownerReadService;
    private final OwnerWriteService ownerWriteService;

    /**
     * 사장님 회원가입
     * @param createOwnerRequestDto
     */
    @PostMapping("/v1/setOwner")
    public void createOwner(CreateOwnerRequestDto createOwnerRequestDto) {
        ownerWriteService.createOwner(createOwnerRequestDto.toDto());
    }

    /**
     * 사장님 아이디 중복체크
     * @param duplicateCheckOwnerEmailRequestDto
     * @return
     */
    @GetMapping("/v1/checkEmail")
    public boolean duplicateCheckOwnerEmail(DuplicateCheckOwnerEmailRequestDto duplicateCheckOwnerEmailRequestDto) {
        return ownerReadService.duplicateCheckOwnerEmail(duplicateCheckOwnerEmailRequestDto.toDto());
    }

    /**
     * 사장님 로그인
     * @param requestDto
     * @return access token
     */
    @GetMapping("/v1/login")
    public String login(LoginRequestDto requestDto) {
        return ownerReadService.login(requestDto.toDto());
    }



}
