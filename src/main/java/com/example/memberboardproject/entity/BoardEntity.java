package com.example.memberboardproject.entity;

import com.example.memberboardproject.dto.BoardSaveDTO;
import com.example.memberboardproject.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    //extends (참조)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private  Long boardId;

    @Column(length = 50, unique = true)
    private String boardWriter;

    @Column(length = 20)
    private String boardPassword;

    @Column(length = 50, unique = true)
    private String boardTitle;

    @Column(length = 50, unique = true)
    private String boardContents;

    // 회원 엔티티와의 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    //Entity 변환
    public static BoardEntity toSaveEntity(BoardSaveDTO boardSaveDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardId(boardEntity.getBoardId());
        boardEntity.setBoardWriter(memberEntity.getMemberEmail());
        boardEntity.setBoardPassword(boardSaveDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    //
    public static BoardEntity toUpdateEntity(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardId(boardUpdateDTO.getBoardId());
        boardEntity.setBoardWriter(boardUpdateDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardUpdateDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
        return  boardEntity;
    }

}
