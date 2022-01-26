package com.example.memberboardproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateDTO {
    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardDate;
}
