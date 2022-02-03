package com.example.memberboardproject.repository;


import com.example.memberboardproject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository  extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByBoardTitleContaining(String keyword);

    List<BoardEntity> findByBoardWriterContaining(String keyword);

    List<BoardEntity> findByBoardContentsContaining(String keyword);
}
