package com.example.dev.service;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.dto.QrcodeImageConfig;
import com.example.dev.entity.Qrcode;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.QrcodeRepository;
import com.example.dev.repository.StoreRepository;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class QrcodeWriteService {

    private final QrcodeRepository qrcodeRepository;
    private final StoreRepository storeRepository;

    /**
     * 큐알코드 생성
     * @param dto
     * @return
     */
    public QrcodeDto createQrcode(QrcodeDto dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("가게 정보를 찾을 수 없습니다. storeId = %s", dto.getStoreId())));
        Qrcode qrcode = qrcodeRepository.saveAndFlush(dto.toEntity(store));
        long qrcodeId = qrcode.getQrcodeId();
        String filePath = createQrCodeImagePath(store.getOwner().getOwnerId(), store.getStoreId(), qrcodeId);

        try {
            QrCodeGenerator.generateQrCodeImage(String.valueOf(qrcodeId), filePath, dto.getQrcodeImageConfig());
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        qrcode.modifyQrcode(Qrcode.builder().qrcodeImage(filePath).build(), false);
        return QrcodeDto.from(qrcode);
    }

    /**
     * 큐알코드 이미지 경로 생성
     * @return
     */
      public String createQrCodeImagePath(long ownerId, long storeId, long qrcodeId) {
        return "./img/" + ownerId + "/" + storeId + "/" + qrcodeId;
    }
}
