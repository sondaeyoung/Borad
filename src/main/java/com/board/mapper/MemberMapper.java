package com.board.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.board.domain.MemberDTO;

@Mapper
public interface MemberMapper {
        public String login(String user_ID); //로그인
        
        public int join(MemberDTO member); // 회원가입
        
        public MemberDTO selectMemberDetail(String user_ID); //마이페이지
        
        public int updateMember(MemberDTO member); //멤버 정보변경
        
        public int deleteMember(String user_ID); // 멤버 탈퇴 = 데이터베이스 유저상태 변경
        
        public List<MemberDTO> selectMemberList(); // 관리자 멤버 관리할때 리스트를 보여줌
        
        public int selectMemberTotalCount();
        
        public int authority(String user_ID); //권한 확인
        
        public int state(String user_ID); //유저 상태 확인
}
