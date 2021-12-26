package com.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.domain.MemberDTO;
import com.board.service.MemberService;
import com.board.util.UiUtils;

@Controller
public class MemberController extends UiUtils {
    
    @Autowired
    private MemberService memberService;
    
    @GetMapping(value = "/board/join.do") //회원가입하기 버튼을 누르거나 주소를 적어서 이동하였을때
    public String openMemberJoin(Model model) {
        model.addAttribute("member", new MemberDTO());
        return "board/join";
    }
    
    @PostMapping(value = "/board/join.do") //회원가입 버튼을 누르고 로그인페이지로 이동하게
    public String registerMember(MemberDTO member, Model model) {
        try {
            boolean isRegistered = memberService.registerMember(member);
            if (isRegistered == false) {
             // TODO => 유저 등록에 실패하였다는 메시지를 전달
                System.out.println("유저 등록에 실패"); // 나중에 알러트해서 알려줘야함 아무튼 일단 여기까지
                return "redirect:/board/join.do";
            }else {
                System.out.println("유저 등록에 성공");
                return showMessageWithRedirect("회원가입 성공", "/board/login.do", Method.GET, null, model);
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
    public String openLogin(Model model,HttpSession session) {
        try {
            if(session.getAttribute("sessionID") != null) {
                session.setAttribute("sessionID", null); //세션 초기화
                session.setAttribute("sessionAuthority", null);
            }
                model.addAttribute("member", new MemberDTO());
                return "board/login";
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("오류뜸");
            return "board/login";
        }
        
     
        
    }
    
    @PostMapping(value = "/board/login.do") //로그인 정보 입력하고 로그인하기 눌렀을때
    public String Login(MemberDTO member,HttpSession session,Model model) {
        String inputID = member.getUser_ID();
        String inputPW = member.getUser_PW();
        
        try {
            
            if(inputID == null || inputID==""||inputPW == null || inputPW == "") {
                System.out.println("아이디 비밀번호가 안적힘");
                return showMessageWithRedirect("아이디와 비밀번호를 적어주세요", "/board/login.do", Method.GET, null, model);
            }else {
                String result = memberService.loginMember(inputID);
                int state = memberService.memberState(inputID); // 유저 상태값 가져오기
                int authority = memberService.memberAuthority(inputID); // 유저 권한값 가져오기
                
                if(inputPW.equals(result)) {
                    if(state == 1) {
                    
                    session.setAttribute("sessionAuthority", authority); // 유저권한값 세션저장
                    session.setAttribute("sessionID", inputID); //유저아이디값 세션저장
                    
                    return showMessageWithRedirect("로그인 되었습니다.", "/board/list.do", Method.GET, null, model);
                    }
                    if(state == 0) {
                        return showMessageWithRedirect("탈퇴한 아이디입니다.", "/board/login.do", Method.GET, null, model);
                    }
                    if(state == 2) {
                        return showMessageWithRedirect("정지된 아이디입니다.", "/board/login.do", Method.GET, null, model);
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(member.getUser_ID());
            return "redirect:/board/login.do";
            
        }
               
        return "redirect:/board/login.do";
    }
    
    @GetMapping(value = "/board/managerPage.do") //관리자가 유저 관리하는 페이지로 가는
    public String openUserList(Model model) {
        
        List<MemberDTO> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);
        
        return "board/managerPage";
    }
    
//    html 스크립트 맵퍼관련
    @PostMapping(value = "/board/idCheck") //동적으로 아래서 알려주려고 
    @ResponseBody //@ResponseBody를 해줘야 데이터값을 html로 보내줌
    public int idCheck(@RequestParam("user_ID") String user_ID) {
        try {
        int db = memberService.idCheck(user_ID);
        System.out.println(db);
        if(db>0) {
            return db;
        }else {
            return db;
        }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("오류뜸");
            return 0;
        }
    }
    
    @PostMapping(value = "/board/nickNameCheck") //동적으로 아래서 알려주려고 
    @ResponseBody //@ResponseBody를 해줘야 데이터값을 html로 보내줌
    public int nickNameCheck(@RequestParam("user_NickName") String user_NickName) {
        try {
        int db = memberService.nickNameCheck(user_NickName);
        System.out.println(db);
        if(db > 0) {
            return db;
        }else {
            return db;
        }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("오류뜸");
            return 0;
        }
    }
    
    @PostMapping(value = "/board/emailCheck") //동적으로 아래서 알려주려고 
    @ResponseBody //@ResponseBody를 해줘야 데이터값을 html로 보내줌
    public int emailCheck(@RequestParam("user_Email") String user_Email) {
        try {
        int db = memberService.emailCheck(user_Email);
        System.out.println(db);
        if(db>0) {
            return db;
        }else {
            return db;
        }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("오류뜸");
            return 0;
        }
    }
    
}
