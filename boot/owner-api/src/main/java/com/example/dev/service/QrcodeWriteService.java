package com.example.dev.service;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.dto.QrcodeImageConfig;
import com.example.dev.dto.response.ModifyQrcodeResposeDto;
import com.example.dev.entity.Qrcode;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.QrcodeRepository;
import com.example.dev.repository.StoreRepository;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@Service
@Slf4j
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
        String qrCodeImage = null;
        String filePath = createQrCodeImagePath(store.getOwner().getOwnerId(), store.getStoreId(), qrcodeId);

        try {
            qrCodeImage = QrCodeGenerator.generateQrCodeImage(String.valueOf(qrcodeId), filePath, dto.getQrcodeImageConfig());
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        qrcode.modifyQrcode(Qrcode.builder().qrcodeImage(qrCodeImage).build(), false);
        return QrcodeDto.from(qrcode);
    }

    /**
     * 큐알코드 이미지 경로 생성
     * @return
     */
      public String createQrCodeImagePath(long ownerId, long storeId, long qrcodeId) {
        return "./img/" + ownerId + "/" + storeId + "/" + qrcodeId;
    }

    /**
     * 큐알코드 수정
     * @param dto
     * @return
     */
    public QrcodeDto modifyQrcode(QrcodeDto dto) {
        Qrcode qrcode = qrcodeRepository.findById(dto.getQrcodeId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 큐알코드 입니다. qrcodeId = %s", dto.getQrcodeId())));
        Store store = qrcode.getStore();
        Long qrcodeId = qrcode.getQrcodeId();
        String changQrcodeImage = null;
        if(dto.getQrcodeImageConfig() != null && !dto.getQrcodeImageConfig().isEmpty()) {
            String filePath = createQrCodeImagePath(store.getOwner().getOwnerId(), store.getStoreId(), qrcodeId);
            try {
                // 신규 큐알코드 이미지 생성
                changQrcodeImage = QrCodeGenerator.generateQrCodeImage(String.valueOf(qrcodeId), filePath, dto.getQrcodeImageConfig());
                // 기존 큐알코드 이미지 삭제
//                Files.delete(Path.of(qrcode.getQrcodeImage()));
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFileException e) {
//                new CommonException(ErrorCode.FILE_DELETE_ERROR, String.format("기존 큐알코드 이미지 파일 삭제 오류 filePath = %s", qrcode.getQrcodeImage()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        qrcode.modifyQrcode(dto.toEntity(store, changQrcodeImage), false);

        return QrcodeDto.from(qrcode);
    }
}
