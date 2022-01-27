package com.example.memberboardproject.dto;


import com.example.memberboardproject.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {
    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardDate;

    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getBoardId());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardDate(boardEntity.getCreateTime());
        return boardDetailDTO;
    }
}

