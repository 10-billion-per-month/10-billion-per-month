package com.example.dev.service;

import com.example.dev.dto.StoreDto;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreWriteService {

    private final StoreRepository storeRepository;
    private final OwnerRepository ownerRepository;

    /**
     * 가게 등록
     * @param dto
     * @return
     */
    public void createStore(StoreDto dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 사장님입니다.", dto.getOwnerId())));
        storeRepository.save(dto.toEntity(owner));
    }

    /**
     * 가게 수정
     * @param dto
     */
    @Transactional
    public void modifyStore(StoreDto dto) {
        // todo 권한 검증

        storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 가게입니다.", dto.getStoreId())))
                .modifyStore(Store.builder()
                        .storeName(dto.getStoreName())
                        .storeStatus(dto.getStoreStatus())
                        .storeOpenTime(dto.getStoreOpenTime())
                        .storeCloseTime(dto.getStoreCloseTime())
                        .storeDescrition(dto.getStoreDescrition())
                        .storeImage(dto.getStoreImage())
                        .build(), false);
    }
}
