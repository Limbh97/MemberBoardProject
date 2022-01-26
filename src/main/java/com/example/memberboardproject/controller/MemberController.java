package com.example.memberboardproject.controller;

import com.example.memberboardproject.dto.MemberDetailDTO;
import com.example.memberboardproject.dto.MemberLoginDTO;
import com.example.memberboardproject.dto.MemberSaveDTO;
import com.example.memberboardproject.service.MemberService;
import com.sun.xml.bind.util.AttributesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.example.memberboardproject.common.SessionConst.LOGIN_EMAIL;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    //회원가입 폼
    @GetMapping("save")
    public String saveForm(){
        return "member/save";
    }
    //회원가입
    @PostMapping("save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO){
        System.out.println("memberController.save");
        ms.save(memberSaveDTO);
        return "index";
    }
    //로그인 폼
    @GetMapping("login")
    public String loginForm(){
        return "member/login";
    }

    //로그인
    @PostMapping("login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session){
        System.out.println("memberController.login");
        boolean login = ms.login(memberLoginDTO);
        if(login){
            session.setAttribute(LOGIN_EMAIL,memberLoginDTO.getMemberEmail());
            return "/index";
        }else {
            System.out.println("memberController.login.else");
            return "member/login";
        }
    }
    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    //전체회원목록 폼
    @GetMapping
    public String findAll(Model model){
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList", memberList);
        return "member/findAll";
    }

    //상세조회
    @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model) {
        MemberDetailDTO member = ms.findById(memberId);
        model.addAttribute("member", member);
        return "member/findById";
    }

    //상세조회(ajax)
    @PostMapping("{memberId}") //동일하면 패스 뒤에 없어도 됨
    public @ResponseBody MemberDetailDTO detail(@PathVariable("memberId") Long memberId) {
        MemberDetailDTO member = ms.findById(memberId);
        return member;
    }

    // 회원삭제
    @GetMapping("delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId) {
        ms.deleteById(memberId);
        return "redirect:/member/";
    }

    // 회원삭제(2번)
    @DeleteMapping("{memberId}")
    public ResponseEntity deleteById2(@PathVariable Long memberId) {
        ms.deleteById(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //정보수정 폼
    @GetMapping("update")
    public String updateForm(Model model, HttpSession session) {
        String memberEmail = (String) session.getAttribute(LOGIN_EMAIL);
        MemberDetailDTO member = ms.findByEmail(memberEmail);
        model.addAttribute("member", member);
        return "member/update";
    }
}
