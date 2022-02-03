package com.example.memberboardproject.service;


import com.example.memberboardproject.dto.CommentDetailDTO;
import com.example.memberboardproject.dto.CommentSaveDTO;
import com.example.memberboardproject.entity.BoardEntity;
import com.example.memberboardproject.entity.CommentEntity;
import com.example.memberboardproject.entity.MemberEntity;
import com.example.memberboardproject.repository.BoardRepository;
import com.example.memberboardproject.repository.CommentRepository;
import com.example.memberboardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceIml implements CommentService{
    private final CommentRepository cr;
    private final BoardRepository br;
    private final MemberRepository mr;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        MemberEntity memberEntity = mr.findByMemberEmail(commentSaveDTO.getCommentWriter());
        CommentEntity commentEntity = CommentEntity.toSaveEntity(commentSaveDTO, boardEntity, memberEntity);
        return cr.save(commentEntity).getId();
    }

    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {
        BoardEntity boardEntity = br.findById(boardId).get();
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        List<CommentDetailDTO> commentList = new ArrayList<>();
        for (CommentEntity c: commentEntityList) {
            CommentDetailDTO commentDetailDTO = CommentDetailDTO.toCommentDetailDTO(c);
            commentList.add(commentDetailDTO);
        }
        return commentList;
    }
}
