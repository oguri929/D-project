package com.mycompany.myapp.member.service;




import com.mycompany.myapp.member.dto.MemberDTO;

public interface MemberService {	
	//회원가입
	public void register(MemberDTO dto) throws Exception;
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;	
	
	//회원정보 수정
	public void memberUpdate(MemberDTO dto) throws Exception;
	
	//아이디 찾기
	public MemberDTO findId(MemberDTO dto) throws Exception;
	
}