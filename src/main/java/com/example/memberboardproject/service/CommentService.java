package com.example.memberboardproject.service;

import com.example.memberboardproject.dto.CommentDetailDTO;
import com.example.memberboardproject.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);
}
