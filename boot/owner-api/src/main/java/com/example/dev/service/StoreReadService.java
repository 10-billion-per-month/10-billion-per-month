package com.example.dev.service;

import com.example.dev.dto.StoreDto;
import com.example.dev.dto.response.StoreResponseDto;
import com.example.dev.dto.response.StoresResponseDto;
import com.example.dev.entity.Store;
import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreReadService {

    private final StoreRepository storeRepository;
    private final OwnerRepository ownerRepository;

    /**
     * 가게 목록 조회 2
     * @param dto
     * @return
     */
    public Page<StoreDto> getStores(StoreDto dto) {
        ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_INPUT_VALUE, String.format("등록되지 않은 사장님입니다.", dto.getOwnerId())));

        return storeRepository.findAllByOwner_OwnerId(dto.getOwnerId(), dto.getPageable()).map(StoreDto::toDto);
    }

    /**
     * 가게 총 개수 조회
     * @param ownerId
     * @return
     */
    public Integer getStoreCount(long ownerId) {
        return storeRepository.countStoreByOwner_OwnerId(ownerId);
    }

    /**
     * 가게 상세 정보 조회
     * @param storeId
     * @return
     */
    public StoreDto getStore(long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        return StoreDto.toDto(store);
    }
}
