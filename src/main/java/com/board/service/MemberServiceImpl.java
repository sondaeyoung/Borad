package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.board.domain.MemberDTO;
import com.board.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    
    @Override
    public MemberDTO loginMember(String user_ID) {
        // TODO Auto-generated method stub
        
        
        
        return memberMapper.login(user_ID);
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

    

}
