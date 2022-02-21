package com.mycompany.myapp.member.service;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;

public interface MemberService {	
	//회원가입
	public void register(MemberDTO dto) throws Exception;
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;	
	
	//회원정보 수정
	public void memberUpdate(MemberDTO dto) throws Exception;
	
	//회원번호로 회원조회
	public MemberDTO selectMemberByNum(int memberNum) throws Exception;
	
	//멤버리스트 가져오기
	public List<MemberDtoContainStudyroom> getMemList(MemberDTO dto);
	
	// 아이디 중복체크
	public int idCheck(String id) throws Exception;
	
	// 아이디 찾기
	public MemberDTO userFindId(MemberDTO dto) throws Exception;
	
	// 회원조회
	public MemberDTO readMember(String id) throws Exception;
	
	//이메일 발송
	public void sendEmail(MemberDTO dto, String div) throws Exception;
	
	// 비밀번호 찾기	
	public void userFindPw(HttpServletResponse response, MemberDTO dto) throws Exception;
	
}