package com.example.memberboardproject.controller;

import com.example.memberboardproject.dto.BoardSaveDTO;
import com.example.memberboardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
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


}
