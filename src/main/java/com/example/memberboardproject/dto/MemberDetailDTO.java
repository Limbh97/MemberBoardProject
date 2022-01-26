package com.example.memberboardproject.dto;

import com.example.memberboardproject.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    // MemberEntity -> MemberDetailDTO
    public static MemberDetailDTO toMemberDetailDTO(MemberEntity memberEntity) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(memberEntity.getMemberId());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());
        return memberDetailDTO;
    }
}
