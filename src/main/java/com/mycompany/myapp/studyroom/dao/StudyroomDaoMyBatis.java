package com.mycompany.myapp.studyroom.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.utils.Criteria;
import com.mycompany.myapp.utils.SearchCriteria;

@Repository
public class StudyroomDaoMyBatis implements StudyroomDao{
	private SqlSessionTemplate sqlSessionTemplate;

	public StudyroomDaoMyBatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<SubjectDto> selectAllSubject() {
		return sqlSessionTemplate.selectList("mapper.studyroom.selectAllSubject");
	}
	
	@Override
	public void insertStudyroom(StudyroomDto studyroomDto) {
		sqlSessionTemplate.insert("mapper.studyroom.insertStudyroom", studyroomDto);
	}

	@Override
	public List<StudyroomDto> selectAllStudyroom(SearchCriteria scri) {
		return sqlSessionTemplate.selectList("mapper.studyroom.selectAllStudyroom", scri);
	}
	
	@Override
	public List<StudyroomDto> selectStudyroomByTag(SearchCriteria scri) {
		return sqlSessionTemplate.selectList("mapper.studyroom.selectStudyroomByTag", scri);
	}

	@Override
	public StudyroomDto selectOneStudyroom(int num) {
		return sqlSessionTemplate.selectOne("mapper.studyroom.selectOneStudyroom", num);
	}

	@Override
	public SubjectDto selectSubject(int subjNum) {
		return sqlSessionTemplate.selectOne("mapper.studyroom.selectSubject", subjNum);
	}
	
	@Override
	public int selectSubjectByName(String addSubject) {
		return sqlSessionTemplate.selectOne("mapper.studyroom.selectSubjectByName", addSubject);
	}

	@Override
	public int updateStudyroomCnt(int num) {
		return sqlSessionTemplate.update("mapper.studyroom.updateStudyroomCnt", num);
	}

	@Override
	public int updateStudyroom(StudyroomDto studyroomDto) {
		return sqlSessionTemplate.update("mapper.studyroom.updateStudyroom", studyroomDto);
	}

	@Override
	public int deleteStudyroom(int num) {
		return sqlSessionTemplate.delete("mapper.studyroom.deleteStudyroom", num);
	}

	@Override
	public int selectTotStudyroom(SearchCriteria scri) {
		return sqlSessionTemplate.selectOne("mapper.studyroom.selectTotStudyroom", scri);
	}

	@Override
	public void insertMember(Map<String, Integer> matchInfo) {
		sqlSessionTemplate.insert("mapper.studyroom.insertMember", matchInfo);	
	}

	@Override
	public List<MemberDTO> selectAllMember(int num) {
		return sqlSessionTemplate.selectList("mapper.studyroom.selectAllMember", num);
	}

	@Override
	public boolean isMember(Map<String, Integer> matchInfo) {
		return sqlSessionTemplate.selectOne("mapper.studyroom.isMember", matchInfo);
	}

	@Override
	public int deleteMember(Map<String, Integer> matchInfo) {
		return sqlSessionTemplate.delete("mapper.studyroom.deleteMember", matchInfo);
	}

	@Override
	public int countTotMember(int chatroomNum) {
		return sqlSessionTemplate.selectOne("mapper.studyroom.countTotMember", chatroomNum);
	}

	@Override
	public int deleteAllMember(int chatroomNum) {
		return sqlSessionTemplate.delete("mapper.studyroom.deleteAllMember", chatroomNum);
	}

	@Override
	public List<Integer> selectStudyroomByCaptain(int captain) {
		return sqlSessionTemplate.selectList("mapper.studyroom.selectStudyroomByCaptain", captain);
	}

	@Override
	public List<Integer> selectMemberFromAllMatch(int memberNum) {
		return sqlSessionTemplate.selectList("mapper.studyroom.selectMemberFromAllMatch", memberNum);
	}

	@Override
	public int deleteMemberFromAllMatch(int memberNum) {
		return sqlSessionTemplate.delete("mapper.studyroom.deleteMemberFromAllMatch", memberNum);
	}

	@Override
	public void insertSubject(SubjectDto subjectDto) {
		sqlSessionTemplate.insert("insertSubject", subjectDto);
	}

	@Override
	public int updateSubject(SubjectDto subjectDto) {
		return sqlSessionTemplate.update("updateSubject", subjectDto);
	}

	@Override
	public int subCheck(String subject) {
		return sqlSessionTemplate.selectOne("subCheck", subject);
	}
	
	
	
	
	
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
