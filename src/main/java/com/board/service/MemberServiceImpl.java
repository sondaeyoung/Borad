package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.board.domain.MemberDTO;
import com.board.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    
    @Override
    public String loginMember(String user_ID) { //로그인하려고 입력된 아이디에 비밀번호 값 가져오기
        // TODO Auto-generated method stub
        
        String result = memberMapper.login(user_ID);
        
        return result;
    }

    @Override
    public boolean registerMember(MemberDTO member) {
        int queryResult = 0;
        
        
            queryResult = memberMapper.join(member);
        
            
        
        return (queryResult == 1) ? true : false;
    }

    @Override
    public MemberDTO getMemberDetail(String user_ID) {
        // TODO Auto-generated method stub
        return memberMapper.selectMemberDetail(user_ID);
    }

    @Override
    public boolean deleteMember(String user_ID) {
        int queryResult = 0;
        
        MemberDTO member = memberMapper.selectMemberDetail(user_ID);
        
        if(member !=null) {
            queryResult = memberMapper.deleteMember(user_ID);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<MemberDTO> getMemberList() {
        List<MemberDTO> memberList = Collections.emptyList();
        int memberTotalCount = memberMapper.selectMemberTotalCount();
        
        if(memberTotalCount >0) {
                memberList = memberMapper.selectMemberList();
        }
        return memberList;
    }
    
    @Override
    public int memberAuthority(String user_ID) { //권한 보기
        // TODO Auto-generated method stub
        
        int result = memberMapper.authority(user_ID);
        
        return result;
    }
    
    @Override
    public int memberState(String user_ID) { //유저 상태 보기
        // TODO Auto-generated method stub
        
        int result = memberMapper.state(user_ID);
        
        return result;
    }
    
    @Override
    public int idCheck(String user_ID) { //아이디 중복체크
        int result = memberMapper.idCheck(user_ID);
        return result;
    }
    
    @Override
    public int nickNameCheck(String user_NickName) { //아이디 중복체크
        int result = memberMapper.nickNameCheck(user_NickName);
        return result;
    }
    
    @Override
    public int emailCheck(String user_Email) { //아이디 중복체크
        int result = memberMapper.emailCheck(user_Email);
        return result;
    }

    

}
