package com.example.memberboardproject.repository;


import com.example.memberboardproject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository  extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByBoardTitleContaining(String keyword);

    List<BoardEntity> findByBoardWriterContaining(String keyword);

    List<BoardEntity> findByBoardContentsContaining(String keyword);

    @Modifying
    @Query("update BoardEntity as b set b.boardHits = b.boardHits+1 where b.id = :boardId")
    void hits(Long boardId);
}
