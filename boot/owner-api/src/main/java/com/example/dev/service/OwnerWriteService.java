package com.example.dev.service;

import com.example.dev.dto.OwnerDto;
import com.example.dev.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class OwnerWriteService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사장님 회원가입
     * @param ownerDto
     */
    public void createOwner(OwnerDto ownerDto) {
        ownerRepository.findByOwnerEmail(ownerDto.getOwnerEmail())
                        .ifPresent(x -> { throw new IllegalStateException("이미 있는 아이디입니다."); });

        ownerRepository.save(ownerDto.toEntity(passwordEncoder));
    }


}
