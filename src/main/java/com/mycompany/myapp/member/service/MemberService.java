package com.mycompany.myapp.member.service;




import java.util.List;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;
import com.mycompany.myapp.member.dto.MemberDtoContainSubjectAndRoomList;

public interface MemberService {	
	//회원가입
	public void register(MemberDTO dto) throws Exception;
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;	
	
	//회원정보 수정
	public void memberUpdate(MemberDTO dto) throws Exception;
	public  List<MemberDtoContainStudyroom> getMemList(MemberDTO dto);
	
}