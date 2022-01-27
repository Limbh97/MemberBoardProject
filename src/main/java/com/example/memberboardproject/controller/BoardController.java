package com.example.memberboardproject.controller;

import com.example.memberboardproject.dto.BoardDetailDTO;
import com.example.memberboardproject.dto.BoardSaveDTO;
import com.example.memberboardproject.dto.BoardUpdateDTO;
import com.example.memberboardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService bs;
    
    //글쓰기 폼
    @GetMapping("/save")
    public String saveForm() {
        return "board/save";
    }

    // 글쓰기
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO){
        Long boardId = bs.save(boardSaveDTO);
        return "index";
    }

    //전체 글목록
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDetailDTO> boardList = bs.findAll();
        model.addAttribute("boardList",boardList);
        System.out.println("boardController.login");
        return "board/findAll";
    }

    // 글목록 조회
    @GetMapping("/{boardId}")
    public String findById(Model model, @PathVariable Long boardId) {
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board", board);
        System.out.println("boardController.login");
        return "board/findById";
    }

    //글목록 조회(ajax)
    @PostMapping("/{boardId}")
    public ResponseEntity findById2(@PathVariable Long boardId) {
        BoardDetailDTO board = bs.findById(boardId);
        return new ResponseEntity<BoardDetailDTO>(board, HttpStatus.OK);
    }

    // 글삭제(ajax)
    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteById2(@PathVariable Long boardId) {
        bs.deleteById(boardId);
        return  new ResponseEntity(HttpStatus.OK);
    }

    // 글 수정 화면 요청
    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model) {
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board", board);
        return "board/update";
    }

    // 글 수정 처리 (post)
    @PostMapping("/update")
    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO) {
        bs.update(boardUpdateDTO);
        return "redirect:/board/" + boardUpdateDTO.getBoardId();
    }

    // 글 수정 처리 (put(ajax))
    @PutMapping("/{boardId}")
    public ResponseEntity update2(@RequestBody BoardUpdateDTO boardUpdateDTO) {
        bs.update(boardUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }



}
