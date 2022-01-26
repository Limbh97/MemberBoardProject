package com.example.memberboardproject.service;

import com.example.memberboardproject.dto.BoardSaveDTO;
import com.example.memberboardproject.entity.BoardEntity;
import com.example.memberboardproject.entity.MemberEntity;
import com.example.memberboardproject.repository.BoardRepository;
import com.example.memberboardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;
    private final MemberRepository mr;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(boardSaveDTO.getBoardWriter());
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardSaveDTO, memberEntity);
        Long boardId = br.save(boardEntity).getBoardId();
        return boardId;
    }
}
