package com.example.memberboardproject.service;

import com.example.memberboardproject.dto.BoardDetailDTO;
import com.example.memberboardproject.dto.BoardSaveDTO;

import java.util.List;

public interface BoardService {
    //글쓰기
    Long save(BoardSaveDTO boardSaveDTO);
    //전체 글목록
    List<BoardDetailDTO> findAll();
    //글목록 조회
    BoardDetailDTO findById(Long boardId);

    void deleteById(Long boardId);
}
