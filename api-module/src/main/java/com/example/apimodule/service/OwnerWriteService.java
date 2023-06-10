package com.example.apimodule.service;

import com.example.apimodule.dto.OwnerDto;
import com.example.apimodule.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class OwnerWriteService {

    private final OwnerRepository ownerRepository;

    /**
     * 사장님 회원가입
     * @param ownerDto
     */
    public void setOwner(OwnerDto ownerDto) {
        ownerRepository.findByOwnerEmail(ownerDto.getOwnerEmail())
                        .ifPresent(x -> { throw new IllegalStateException("이미 있는 아이디입니다."); });

        ownerRepository.save(ownerDto.toEntity());
    }


}
