package com.example.memberboardproject.service;

import com.example.memberboardproject.dto.MemberDetailDTO;
import com.example.memberboardproject.dto.MemberLoginDTO;
import com.example.memberboardproject.dto.MemberSaveDTO;
import com.example.memberboardproject.entity.MemberEntity;
import com.example.memberboardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository mr;

//회원가입
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        System.out.println("MemberServiceImpl.save");
        return mr.save(memberEntity).getMemberId();
    }

//로그인
    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if (memberEntity != null) {
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

//전체회원목록
    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for (MemberEntity m : memberEntityList) {
            memberList.add(MemberDetailDTO.toMemberDetailDTO(m));
        }
        return memberList;
    }

        //상세조회
        @Override
        public MemberDetailDTO findById(Long memberId) {
            return MemberDetailDTO.toMemberDetailDTO(mr.findById(memberId).get());
    }

        //삭제
        @Override
        public void deleteById(Long memberId) {
            mr.deleteById(memberId);
        }

        //정보수정
        @Override
        public MemberDetailDTO findByEmail(String memberEmail) {
            MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
            MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);
            return memberDetailDTO;
    }
        
        //중복체크
        @Override
        public String emailDp(String MemberEmail) {
            MemberEntity emailCheckResult = mr.findByMemberEmail(MemberEmail);
            if (emailCheckResult ==null) {
                return "ok";
            }else {
                return "no";
            }
        }

}



