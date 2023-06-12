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
@Transactional
public class StoreWriteService {

    private final StoreRepository storeRepository;
    private final OwnerRepository ownerRepository;

    /**
     * 가게 등록
     *
     * @param dto
     * @return
     */
    public void setStore(StoreDto dto) {
//        ownerRepository.findById(dto.getOwnerId())
//                .ifPresentOrElse(
//                        owner -> {
//                            storeRepository.save(dto.toEntity(owner));
//                        },
//
//                        () -> {
//                            throw new NullPointerException("등록되지 않은 사장님입니다.");
//                        }
//                );

        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("사장님 아이디가 없습니다. %d", dto.getOwnerId())));

//        Owner owner1 = ownerRepository.findById(dto.getOwnerId())
//                .orElse(Owner.builder().build());
//
//        Owner owner2 = ownerRepository.findById(dto.getOwnerId())
//                .orElseGet(() -> {
//                    return Owner.builder().build();
//                });
        Store store = storeRepository.getReferenceById(dto.getStoreId());

//        storeRepository.save(dto.toEntity(owner));
    }
}
