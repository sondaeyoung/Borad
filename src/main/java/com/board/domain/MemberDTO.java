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
    
    public MemberDTO(int user_Number, String user_ID, String user_PW, String user_Name, String user_NickName,
            String user_Gender, String user_DOB, String user_Email, String user_PhoneNumber, String user_Address,
            LocalDateTime user_JoinDate, LocalDateTime user_SecessionDate, LocalDateTime user_StopDate,
            int user_Authority, int user_State) {
        super();
        this.user_Number = user_Number;
        this.user_ID = user_ID;
        this.user_PW = user_PW;
        this.user_Name = user_Name;
        this.user_NickName = user_NickName;
        this.user_Gender = user_Gender;
        this.user_DOB = user_DOB;
        this.user_Email = user_Email;
        this.user_PhoneNumber = user_PhoneNumber;
        this.user_Address = user_Address;
        this.user_JoinDate = user_JoinDate;
        this.user_SecessionDate = user_SecessionDate;
        this.user_StopDate = user_StopDate;
        this.user_Authority = user_Authority;
        this.user_State = user_State;
    }

    public MemberDTO() {
        // TODO Auto-generated constructor stub
    }
    
    
    
}
