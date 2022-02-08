package com.mycompany.myapp.studyroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.studyroom.dao.StudyroomDao;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.utils.SearchCriteria;

@Service
public class StudyroomServiceImpl implements StudyroomService {
	private StudyroomDao studyroomDao;
	
	public StudyroomServiceImpl(StudyroomDao studyroomDao) {
		this.studyroomDao = studyroomDao;
	}

	@Override
	public List<SubjectDto> getSubjectList() {
		return studyroomDao.selectAllSubject();
	}
	
	@Override
	public void addSubject(String addSubject) {
		studyroomDao.insertSubject(addSubject);
	}

	@Override
	public void createStudyroom(StudyroomDto studyroomDto) {
		studyroomDao.insertStudyroom(studyroomDto);
	}

	@Override
	public List<StudyroomDto> listStudyroom(SearchCriteria scri) {
		return studyroomDao.selectAllStudyroom(scri);
	}
	
	@Override
	public List<StudyroomDto> listStudyroom(int num) {
		return studyroomDao.selectStudyroomByTag(num);
	}

	@Override
	public StudyroomDto readStudyroom(int num) {
		studyroomDao.updateStudyroomCnt(num);
		return studyroomDao.selectOneStudyroom(num);
	}

	@Override
	public SubjectDto getSubject(int subjNum) {
		return studyroomDao.selectSubject(subjNum);
	}

	@Override
	public int getSubjectByName(String addSubject) {
		return studyroomDao.selectSubjectByName(addSubject);
	}

	@Override
	public int editStudyroom(StudyroomDto studyroomDto) {
		return studyroomDao.updateStudyroom(studyroomDto);
	}

	@Override
	public int deleteStudyroom(int num) {
		return studyroomDao.deleteStudyroom(num);
	}

	@Override
	public int countTotStudyroom(SearchCriteria scri) {
		return studyroomDao.selectTotStudyroom(scri);
	}


	
	
	
	
	

	
	
	
	
	
}
