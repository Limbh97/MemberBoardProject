package com.example.memberboardproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveDTO {
    private String MemberEmail;
    private String MemberPassword;
    private String MemberName;

}
