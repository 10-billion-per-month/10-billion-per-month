package com.example.dev.controller;

import com.example.dev.dto.request.CreateQrcodeRequestDto;
import com.example.dev.dto.request.DeleteQrcodeRequestDto;
import com.example.dev.dto.request.ModifyQrcodeRequestDto;
import com.example.dev.dto.request.QrcodesRequestDto;
import com.example.dev.dto.response.CreateQrcodeResponseDto;
import com.example.dev.dto.response.ModifyQrcodeResposeDto;
import com.example.dev.dto.response.QrcodesResponseDto;
import com.example.dev.service.QrcodeReadService;
import com.example.dev.service.QrcodeWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public CreateQrcodeResponseDto createQrcode(@Valid @RequestBody CreateQrcodeRequestDto requestDto) {
        return CreateQrcodeResponseDto.from(qrcodeWriteService.createQrcode(requestDto.toDto()));
    }

    /**
     * 가게의 큐알코드 목록 조회
     * @param requestDto
     * @param pageable
     * @return
     */
    @GetMapping("/v1/qrcodes")
    public Page<QrcodesResponseDto> getQrcodes(@Valid QrcodesRequestDto requestDto, @PageableDefault(size = 10, page = 0, sort = "qrcodeId", direction = Sort.Direction.ASC) Pageable pageable) {
        return qrcodeReadService.getQrcodes(requestDto.toDto(), pageable).map(QrcodesResponseDto::from);
    }

    /**
     * 큐알 코드 수정
     * @param requestDto
     * @return
     */
    @PutMapping("/v1/qrcode")
    public ModifyQrcodeResposeDto modifyQrcode(@Valid @RequestBody ModifyQrcodeRequestDto requestDto) {
        return ModifyQrcodeResposeDto.from(qrcodeWriteService.modifyQrcode(requestDto.toDto()));
    }

    @DeleteMapping("/v1/qrcode")
    public void deleteQrcode(DeleteQrcodeRequestDto requestDto) {
        qrcodeWriteService.deleteQrcode(requestDto.toDto());
    }
}
