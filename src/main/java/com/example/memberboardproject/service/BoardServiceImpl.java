package com.example.memberboardproject.service;

import com.example.memberboardproject.common.PagingConst;
import com.example.memberboardproject.dto.BoardDetailDTO;
import com.example.memberboardproject.dto.BoardPagingDTO;
import com.example.memberboardproject.dto.BoardSaveDTO;
import com.example.memberboardproject.dto.BoardUpdateDTO;
import com.example.memberboardproject.entity.BoardEntity;
import com.example.memberboardproject.entity.MemberEntity;
import com.example.memberboardproject.repository.BoardRepository;
import com.example.memberboardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;
    private final MemberRepository mr;
    //글쓰기
    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(boardSaveDTO.getBoardWriter());
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardSaveDTO, memberEntity);
        Long boardId = br.save(boardEntity).getId();
        return boardId;
    }

    // 전체 글목록 조회
    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll();
        List<BoardDetailDTO> boardList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardList.add(BoardDetailDTO.toBoardDetailDTO(boardEntity));
        }
        return boardList;
    }

    //글목록 조회
    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        BoardDetailDTO boardDetailDTO = null;
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        }
        return boardDetailDTO;
    }

    //글 삭제(ajax)
    @Override
    public void deleteById(Long boardId) {
        br.deleteById(boardId);
    }
    //글 수정 (post, put(ajax))
    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardUpdateDTO);
        return br.save(boardEntity).getId();
    }

    //페이징
    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
        page = (page == 1)? 0 : (page-1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        // Page<BoardEntity> => Page<BoardPagingDTO>
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle())
        );
        return boardList;
    }
}
