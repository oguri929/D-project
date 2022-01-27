package com.mycompany.myapp.studyroom.dao;

import java.util.List;

import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.utils.SearchCriteria;

public interface StudyroomDao {
	//과목 리스트 가져오기
	public abstract List<SubjectDto> selectAllSubject();
	//스터디룸 삽입
	public abstract void insertStudyroom(StudyroomDto studyroomDto);
	//모든 스터디룸 조회
	public abstract List<StudyroomDto> selectAllStudyroom(SearchCriteria scri);
	//스터디룸 한 개 가져오기
	public abstract StudyroomDto selectOneStudyroom(int num);
	//스터디룸 조회수 증가시키기
	public abstract int updateStudyroomCnt(int num);
	//등록된 과목 이름 가져오기
	public abstract SubjectDto selectSubject(int subjNum);
	//스터디룸 업데이트하기
	public abstract int updateStudyroom(StudyroomDto studyroomDto);
	//스터디룸 삭제하기
	public abstract int deleteStudyroom(int num);
	//총 스터디룸 개수 조회
	public abstract int selectTotStudyroom(SearchCriteria scri);
}
