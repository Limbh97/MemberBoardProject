package com.example.memberboardproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDTO {
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    //파일 업로드
    private MultipartFile boardFile;
    //파일 타입, 이름 지정
    private String boardFileName;
}
