package com.example.dev.controller;

import com.example.dev.dto.request.CreateQrcodeRequestDto;
import com.example.dev.dto.response.CreateQrcodeResponseDto;
import com.example.dev.service.QrcodeReadService;
import com.example.dev.service.QrcodeWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QrcodeController {

    private final QrcodeWriteService qrcodeWriteService;
    private final QrcodeReadService qrcodeReadService;

    /**
     * 큐알코드 등록
     * @param requestDto
     * @return
     */
    @PostMapping("/v1/qrcode")
    public CreateQrcodeResponseDto createQrcode(@Valid CreateQrcodeRequestDto requestDto) {
        return CreateQrcodeResponseDto.from(qrcodeWriteService.createQrcode(requestDto.toDto()));
    }
}
