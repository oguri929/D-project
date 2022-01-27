package com.mycompany.myapp.studyroom.service;

import java.util.List;

import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.utils.SearchCriteria;

public interface StudyroomService {
	//과목 목록 가져오기
	public abstract List<SubjectDto> getSubjectList();
	//스터디룸 만들기
	public abstract void createStudyroom(StudyroomDto studyroomDto);
	//스터디룸 리스트 가져오기
	public abstract List<StudyroomDto> listStudyroom(SearchCriteria scri);
	//스터디룸 세부정보 보기
	public abstract StudyroomDto readStudyroom(int num);
	//스터디룸에 등록된 과목 이름정보 가져오기
	public abstract SubjectDto getSubject(int subjNum);
	//스터디룸 수정하기
	public abstract int editStudyroom(StudyroomDto studyroomDto);
	//스터디룸 삭제하기
	public abstract int deleteStudyroom(int num);
	//스터디룸 전체 개수 가져오기
	public abstract int countTotStudyroom(SearchCriteria scri);
}
