package com.mycompany.myapp.member.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;


@Repository // 현재 클라스를 dao bean 으로 등록
public class MemberDAOImpl implements MemberDAO {

	private SqlSessionTemplate sqlSessionTemplate;
	

	public MemberDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + "login", dto);
	}

	

	// 회원가입
	@Override
	public void register(MemberDTO dto) throws Exception {
		sqlSessionTemplate.insert("register", dto);
		
	}
	
	// 회원정보 수정
	// 서비스에서 보낸 파라미터들을 memberUpdate(MemberDTO dto)에 담는다
		
	
	

	@Override
	public void memberUpdate(MemberDTO dto) throws Exception {
		//vo 에 담긴 파라미터들은 member(mapper).xml에 MemberMapper라는 namespace에
		// 아이디가 memberUpdate인 쿼리에 파라미터들을 넣어준다
		sqlSessionTemplate.update("memberUpdate", dto);
		
	}

	@Override
	public MemberDTO selectMemberByNum(int memberNum) throws Exception {
		return sqlSessionTemplate.selectOne("selectMemberByNum", memberNum);
	}

	@Override
	public List<MemberDtoContainStudyroom> getStudyroomInfo(MemberDTO dto) {
		return sqlSessionTemplate.selectList("mapper.studyroom.selectStudyroomByMemberDto",dto);
	}
	
	//아이디 중복체크	
	@Override
	public int idCheck(String id) throws Exception {
		int result = sqlSessionTemplate.selectOne("idCheck", id);
		return result;
	}
	
	// 아이디 찾기
	@Override
	public MemberDTO userFindId(MemberDTO dto) throws Exception {
		return sqlSessionTemplate.selectOne("userFindId", dto);
	}
	
	// 회원 조회
	@Override
	public MemberDTO readMember(String id) throws Exception {
		return sqlSessionTemplate.selectOne("readMember", id);
	}
	
	// 비밀번호 변경
	@Override
	public int userFindPw(MemberDTO dto) throws Exception {
		return sqlSessionTemplate.update("userFindPw", dto);
	}
	


	
	
	
	
	
	
	
	
	
	
	

	
	

	
	
	
	
	
	
	


	
	
	
}
