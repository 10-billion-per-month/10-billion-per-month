package com.example.dev.service;

import com.example.dev.dto.StoreDto;
import com.example.dev.entity.Owner;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
        Owner owner = ownerRepository.findById(dto.getOwnerId()).get();
        if(owner == null) throw new NullPointerException("등록되지 않은 사장님입니다.");
        storeRepository.save(dto.toEntity(owner));
    }
}
