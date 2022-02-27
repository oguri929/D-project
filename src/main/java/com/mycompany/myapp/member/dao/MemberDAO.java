package com.mycompany.myapp.member.dao;




import java.util.List;


import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;


public interface MemberDAO {
	String NAMESPACE = "member.";
	
	// 회원가입
	public void register(MemberDTO dto) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
	// 회원정보 수정
	public void memberUpdate(MemberDTO dto) throws Exception;
	
	//회원번호로 회원 조회
	public MemberDTO selectMemberByNum(int memberNum) throws Exception;
	
	//회원번호로 스터디룸 정보 조회
	public List<MemberDtoContainStudyroom> getStudyroomInfo(MemberDTO dto);
	
	// 아이디 중복체크
	public int idCheck(String id) throws Exception;
	
	// 아이디 찾기
	public MemberDTO userFindId(MemberDTO dto) throws Exception;
	
	// 회원조회
	public MemberDTO readMember(String id) throws Exception;
		
	// 비밀번호 인증
	public int userFindPw(MemberDTO dto) throws Exception;

	//멤버 삭제
	public int deleteMember(String id) throws Exception;





	
	
}
