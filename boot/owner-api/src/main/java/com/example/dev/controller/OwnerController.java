package com.example.dev.controller;

import com.example.dev.dto.DuplicateCheckOwnerEmailRequestDto;
import com.example.dev.dto.SetOwnerRequestDto;
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
     * @param setOwnerRequestDto
     */
    @PostMapping("/v1/setOwner")
    public void setOwner(SetOwnerRequestDto setOwnerRequestDto) {
        ownerWriteService.setOwner(setOwnerRequestDto.toDto());
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



}
