package com.example.dev.service;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.dto.request.QrcodesRequestDto;
import com.example.dev.entity.Qrcode;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.QrcodeRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QrcodeReadService {

    private final QrcodeRepository qrcodeRepository;
    private final StoreRepository storeRepository;

    /**
     * 가게의 큐알코드 목록 조회
     * @param requestDto
     * @param pageable
     * @return
     */
    public Page<QrcodeDto> getQrcodes(QrcodeDto dto, Pageable pageable) {
        Long storeId = dto.getStoreId();
        storeRepository.findById(storeId)
                        .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("존재하지 않는 가게입니다. storeId = %s", storeId)));
        return qrcodeRepository.findAllByStore_StoreId(storeId, pageable)
                .map(QrcodeDto::from);
    }
}
