package com.mycompany.myapp.member.dao;


import com.mycompany.myapp.member.dto.MemberDTO;


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
	
}
