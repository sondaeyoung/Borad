package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    
    private int user_Number; //회원번호
    
    private String user_ID;  //아이디
    private String user_PW;  //비밀번호
    private String user_Name;   //이름
    private String user_NickName;   //닉네임
    private String user_Gender; //성별
    private String user_DOB;    //생년월일
    private String user_Email;  //이메일
    private String user_PhoneNumber;    //휴대전화번호
    private String user_Address; // 주소
    
    private LocalDateTime user_JoinDate;       //가입일
    private LocalDateTime user_SecessionDate;  //탈퇴일
    private LocalDateTime user_StopDate; // 정지일
    private int user_Authority; //권한 레벨 0:관리자 1: 유저,, 자동으로 1로
    private int user_State; // 활동상태 0:탈퇴 1:활동중 2:정지
}
