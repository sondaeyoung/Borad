package com.board.service;

import java.util.List;

import com.board.domain.MemberDTO;

public interface MemberService {
        
    public boolean registerMember(MemberDTO member);
    
    public MemberDTO getMemberDetail(String user_ID);
    
    //public boolean updateMember(MemberDTO member);
    
    public boolean deleteMember(String user_ID);
    
    public List<MemberDTO> getMemberList();
    
    public MemberDTO loginMember(String user_ID);
}
