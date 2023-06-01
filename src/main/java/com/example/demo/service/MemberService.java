package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.entity.Member;
import com.example.demo.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 정보 조회
     * @param dto
     * @return
     */
    public MemberResponseDto getMember(MemberDto dto) {

        // 이름으로 조회
        return MemberResponseDto.toResponseDto(memberRepository.findByName(dto.getName()));
    }

    /**
     * 회원 등록
     * @param dto
     */
    public void setMember(MemberDto dto) {
        memberRepository.save(dto.toEntity());
    }

    /**
     * 회원 정보 수정
     * @param dto
     */
    public void editMember(MemberDto dto) {
        memberRepository.save(dto.toEntity());
    }

    /**
     * 회원 삭제
     * @param dto
     */
    public void delMember(MemberDto dto) {
        memberRepository.deleteById(dto.getId());
    }
}
