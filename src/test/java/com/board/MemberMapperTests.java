package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.MemberDTO;
import com.board.mapper.MemberMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;
    
    @Test
    public void testofInsertMember() {
        MemberDTO member = new MemberDTO();
        member.setUser_ID("test2");
        member.setUser_PW("1234");
        member.setUser_Name("tester1");
        member.setUser_NickName("testtt1");
        member.setUser_Gender("남자");
        member.setUser_DOB("921227");
        member.setUser_Email("ppeecc@naver.com");
        member.setUser_PhoneNumber("01044909825");
        member.setUser_Address("우리집 앞마당");

        int result = memberMapper.join(member);
        System.out.println("결과는 " + result + "입니다.");
    }
    
    @Test
    public void testOfSelectDetail() {
        MemberDTO member = memberMapper.selectMemberDetail((String) "test1");
        try {
            String memberJson = new ObjectMapper().writeValueAsString(member);

            System.out.println("=========================");
            System.out.println(memberJson);
            System.out.println("=========================");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testOfUpdate() {
        MemberDTO member = new MemberDTO();
        member.setUser_PW("4321");
        member.setUser_NickName("수정닉");
        member.setUser_Address("수정주소");
        member.setUser_ID("test1");
        

        int result = memberMapper.updateMember(member);
        if (result == 1) {
            MemberDTO member2 = memberMapper.selectMemberDetail((String) "test1");
            try {
                String member2Json = new ObjectMapper().writeValueAsString(member2);

                System.out.println("=========================");
                System.out.println(member2Json);
                System.out.println("=========================");

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                System.out.println("잘못됨");
            }
        }
    }
    
    @Test
    public void testOfDelete() {
        int result = memberMapper.deleteMember((String) "test1");
        if (result == 1) {
            MemberDTO member = memberMapper.selectMemberDetail((String) "test1");
            try {
                String memberJson = new ObjectMapper().writeValueAsString(member);

                System.out.println("=========================");
                System.out.println(memberJson);
                System.out.println("=========================");

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void tsetMemberTotalCount() {
        MemberDTO member = new MemberDTO();
        

        int result = memberMapper.join(member);
        System.out.println("결과는 " + result + "입니다.");
    }
}
