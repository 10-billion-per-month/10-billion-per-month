package com.example.demo.respository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);
    void updateMember
}
