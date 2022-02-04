package com.example.memberboardproject.controller;

import com.example.memberboardproject.common.PagingConst;
import com.example.memberboardproject.dto.*;
import com.example.memberboardproject.service.BoardService;
import com.example.memberboardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService bs;
    private final CommentService cs;
    
    //글쓰기 폼
    @GetMapping("/save")
    public String saveForm() {
        return "board/save";
    }

    // 글쓰기
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException{
        System.out.println("boardSaveDTO = " + boardSaveDTO);
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
    public String findById(Model model, @PathVariable("boardId") Long boardId) {
        log.info("글보기 메서드 호출. 요청글 번호: {}", boardId);
        bs.hits(boardId);
        BoardDetailDTO board = bs.findById(boardId);
        List<CommentDetailDTO> commentList = cs.findAll(boardId);
        model.addAttribute("board", board);
        model.addAttribute("commentList", commentList);
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

    //페이징 처리
    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/paging";
    }

    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("넘어옴? 굳?");
        return "index";
    }

    // 검색
    @GetMapping("/search")
    public String search(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword, Model model) {
        System.out.println("searchType1 = " + searchType);
        System.out.println("keyword1 = " + keyword);

        List<BoardDetailDTO> boardList = bs.search(searchType,keyword);
        System.out.println("boardList" + boardList);

        System.out.println("searchType2 = " + searchType);
        System.out.println("keyword2 = " + keyword);

        model.addAttribute("boardList",boardList);
        System.out.println("boardList2 = " + boardList);

        return "/board/findAll";
    }



}
