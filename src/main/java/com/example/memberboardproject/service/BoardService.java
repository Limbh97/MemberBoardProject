package com.example.memberboardproject.service;

import com.example.memberboardproject.dto.BoardDetailDTO;
import com.example.memberboardproject.dto.BoardPagingDTO;
import com.example.memberboardproject.dto.BoardSaveDTO;
import com.example.memberboardproject.dto.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    //글쓰기
    Long save(BoardSaveDTO boardSaveDTO) throws IOException;
    //전체 글목록
    List<BoardDetailDTO> findAll();
    //글목록 조회
    BoardDetailDTO findById(Long boardId);
    //글 삭제
    void deleteById(Long boardId);
    //글 수정(post, put(ajax)
    Long update(BoardUpdateDTO boardUpdateDTO);
    //페이징
    Page<BoardPagingDTO> paging(Pageable pageable);
    //댓글, 검색
    List<BoardDetailDTO> search(String searchType, String keyword);
    //조회수
    void hits(Long boardId);
}
