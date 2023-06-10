package com.example.dev.service;

import com.example.dev.dto.OwnerDto;
import com.example.dev.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OwnerReadService {

    private final OwnerRepository ownerRepository;

    /**
     * 사장님 아이디 중복 체크 : > 0 중복
     * @param ownerDto
     * @return
     */
    public boolean duplicateCheckOwnerEmail(OwnerDto ownerDto) {

        // todo 이메일 검증 기준 추가 필요

        //Regular Expression
        String regx = "^(.+)@(.+)$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regx);
        //Create instance of matcher
        Matcher matcher = pattern.matcher(ownerDto.getOwnerEmail());
        if (!matcher.matches()) throw new IllegalArgumentException("이메일 형식이 아닙니다.");

        return ownerRepository.existsOwnerByOwnerEmail(ownerDto.getOwnerEmail());
    }

}
