package com.example.memberboardproject.repository;

import com.example.memberboardproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //중복체크?
    MemberEntity findByMemberEmail(String memberEmail);
}
