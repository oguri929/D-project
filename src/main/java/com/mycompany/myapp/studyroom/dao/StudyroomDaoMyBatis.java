package com.mycompany.myapp.studyroom.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.utils.SearchCriteria;

@Repository
public class StudyroomDaoMyBatis implements StudyroomDao{
	private SqlSessionTemplate sqlSessionTemplate;

	public StudyroomDaoMyBatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<SubjectDto> selectAllSubject() {
		return sqlSessionTemplate.selectList("selectAllSubject");
	}

	@Override
	public void insertStudyroom(StudyroomDto studyroomDto) {
		sqlSessionTemplate.insert("insertStudyroom", studyroomDto);
	}

	@Override
	public List<StudyroomDto> selectAllStudyroom(SearchCriteria scri) {
		return sqlSessionTemplate.selectList("selectAllStudyroom", scri);
	}

	@Override
	public StudyroomDto selectOneStudyroom(int num) {
		return sqlSessionTemplate.selectOne("selectOneStudyroom", num);
	}

	@Override
	public SubjectDto selectSubject(int subjNum) {
		return sqlSessionTemplate.selectOne("selectSubject", subjNum);
	}

	@Override
	public int updateStudyroomCnt(int num) {
		return sqlSessionTemplate.update("updateStudyroomCnt", num);
	}

	@Override
	public int updateStudyroom(StudyroomDto studyroomDto) {
		return sqlSessionTemplate.update("updateStudyroom", studyroomDto);
	}

	@Override
	public int deleteStudyroom(int num) {
		return sqlSessionTemplate.delete("deleteStudyroom", num);
	}

	@Override
	public int selectTotStudyroom(SearchCriteria scri) {
		return sqlSessionTemplate.selectOne("selectTotStudyroom", scri);
	}
	
	
	
	
	
	
	
	
}
