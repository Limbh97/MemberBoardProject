package com.example.memberboardproject.controller;

import com.example.memberboardproject.dto.MemberLoginDTO;
import com.example.memberboardproject.dto.MemberSaveDTO;
import com.example.memberboardproject.service.MemberService;
import com.sun.xml.bind.util.AttributesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.example.memberboardproject.common.SessionConst.LOGIN_EMAIL;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    //회원가입폼
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
    //로그인폼
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
            return "member/login";
        }
    }
}
