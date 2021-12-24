package com.board.service;

import java.util.List;

import com.board.domain.MemberDTO;

public interface MemberService {
        
    public boolean registerMember(MemberDTO member);
    
    public MemberDTO getMemberDetail(String user_ID);
    
    //public boolean updateMember(MemberDTO member);
    
    public boolean deleteMember(String user_ID);
    
    public List<MemberDTO> getMemberList();
    
    public String loginMember(String user_ID);

    public int memberAuthority(String user_ID);
    
    public int memberState(String user_ID);

    public int idCheck(String user_ID);

    public int nickNameCheck(String user_NickName);

    public int emailCheck(String user_Email);
}
