package com.example.memberboardproject.service;

import com.example.memberboardproject.dto.MemberDetailDTO;
import com.example.memberboardproject.dto.MemberLoginDTO;
import com.example.memberboardproject.dto.MemberSaveDTO;

import java.util.List;

public interface MemberService {
    //회원가입
    Long save(MemberSaveDTO memberSaveDTO);
    //로그인
    boolean login(MemberLoginDTO memberLoginDTO);
    //전체회원목록조회
    List<MemberDetailDTO> findAll();
}
