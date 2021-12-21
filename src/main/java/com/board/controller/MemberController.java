package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.domain.MemberDTO;
import com.board.service.MemberService;

@Controller
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @GetMapping(value = "/board/join.do") //회원가입하기 버튼을 누르거나 주소를 적어서 이동하였을때
    public String openMemberJoin(Model model) {
        model.addAttribute("member", new MemberDTO());
        return "board/join";
    }
    
    @PostMapping(value = "/board/join.do") //회원가입 버튼을 누르고 로그인페이지로 이동하게
    public String registerMember(MemberDTO member) {
        try {
            boolean isRegistered = memberService.registerMember(member);
            if (isRegistered == false) {
             // TODO => 유저 등록에 실패하였다는 메시지를 전달
                System.out.println("유저 등록에 실패");
                return "redirect:/board/join.do";
            }else {
                System.out.println("유저 등록에 성공");
                return "redirect:/board/login.do";
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
            System.out.println("데이터베이스 문제 ");

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
            System.out.println("시스템에 문제 실패");
        }

        return "redirect:/board/login.do"; // 문제없이 잘됬다는거니깐 회원가입한 정보로 로그인하러 ㄱ.
    }
    
    

    @GetMapping(value = "/board/login.do") //로그인 주소로 이동할때
    public String openLogin(Model model) {
        model.addAttribute("member", new MemberDTO());
        return "board/login";
    }
    
    @PostMapping(value = "/board/login.do") //로그인 정보 입력하고 로그인하기 눌렀을때
    public String Login(MemberDTO member) {
        try {
            if(member.getUser_ID() == null || member.getUser_PW() == null) {
                System.out.println("아이디 비밀번호가 안적힘");
                return "redirect:/board/login.do";
            }else {
                String inputPW = member.getUser_PW();
                MemberDTO result = memberService.loginMember(member.getUser_ID());
                
                if(inputPW == result.getUser_PW()) {
                    System.out.println("같으면 로그인성공");
                    return "redirect:/board/join.do";
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("뭔가ㅓ 오류뜸");
            return "redirect:/board/login.do";
            
        }
        return "redirect:/board/join.do";
    }
    
    @GetMapping(value = "/board/managerPage.do") //관리자가 유저 관리하는 페이지로 가는
    public String openUserList(Model model) {
        
        List<MemberDTO> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);
        
        return "board/managerPage";
    }
}
