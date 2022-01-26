package com.example.memberboardproject.entity;

import com.example.memberboardproject.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(length = 50)
    private String memberEmail;

    @Column(length = 50)
    private String memberPassword;

    @Column(length = 20)
    private String memberName;


    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO){
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        return memberEntity;
    }

}
