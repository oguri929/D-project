package com.mycompany.myapp.member.service;



import org.springframework.stereotype.Service;

import com.mycompany.myapp.member.dao.MemberDAO;
import com.mycompany.myapp.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDao;
	

	public MemberServiceImpl(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return memberDao.login(dto);
	}
	
	
	
	
	


	// 회원가입
	@Override
	public void register(MemberDTO dto) throws Exception {
		memberDao.register(dto);
		
	}
	// 회원정보 수정
	// Controller에서 보내는 파라미터들을 memberUpdate(MemberDTO dto)로 받고
	@Override
	public void memberUpdate(MemberDTO dto) throws Exception {
		// 받은 dto를 DAO로 보내준다
		memberDao.memberUpdate(dto);
		
	}
	
	

	
}
	

	
