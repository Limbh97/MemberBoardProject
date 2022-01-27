package com.example.memberboardproject.controller;

import com.example.memberboardproject.dto.BoardDetailDTO;
import com.example.memberboardproject.dto.BoardSaveDTO;
import com.example.memberboardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // 글목록
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDetailDTO> boardList = bs.findAll();
        model.addAttribute("boardList",boardList);
        System.out.println("boardController.login");
        return "board/findAll";
    }



}
