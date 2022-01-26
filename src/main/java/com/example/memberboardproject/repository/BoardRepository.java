package com.example.memberboardproject.repository;


import com.example.memberboardproject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository  extends JpaRepository<BoardEntity, Long> {
}
