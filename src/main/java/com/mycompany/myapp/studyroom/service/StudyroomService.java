package com.mycompany.myapp.studyroom.service;

import java.util.List;
import java.util.Map;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.utils.SearchCriteria;

public interface StudyroomService {
	//과목 목록 가져오기
	public abstract List<SubjectDto> getSubjectList();
	//과목 추가
	public abstract void addSubject(String addSubject);
	//스터디룸 만들기
	public abstract void createStudyroom(StudyroomDto studyroomDto);
	//스터디룸 리스트 가져오기
	public abstract List<StudyroomDto> listStudyroom(SearchCriteria scri);
	//스터디룸 리스트 가져오기
	public abstract List<StudyroomDto> listStudyroomByTag(SearchCriteria scri);
	//스터디룸 세부정보 보기
	public abstract StudyroomDto readStudyroom(int num);
	//스터디룸에 등록된 과목 이름정보 가져오기
	public abstract SubjectDto getSubject(int subjNum);
	//과목이름으로 과목 번호 가져오기
	public abstract int getSubjectByName(String addSubject);
	//스터디룸 수정하기
	public abstract int editStudyroom(StudyroomDto studyroomDto);
	//스터디룸 삭제하기
	public abstract int deleteStudyroom(int num);
	//스터디룸 전체 개수 가져오기
	public abstract int countTotStudyroom(SearchCriteria scri);
	//스터디룸에 멤버 등록
	public abstract void addMember(Map<String, Integer> matchInfo);
	//스터디룸에 속해 있는 멤버 정보 가져오기
	public abstract List<MemberDTO> getMemberList(int num);
	//스터디룸에서 멤버 삭제
	public abstract int deleteMember(Map<String, Integer> matchInfo);
	//스터디룸에 있는 멤버 수 세기
	public abstract int countTotMember(int chatroomNum);
	//스터디룸에 멤버 있는지 확인
	public abstract boolean isMember(Map<String, Integer> matchInfo);
	//방장으로 스터디룸 조회
	public abstract List<Integer> selectStudyroomByCaptain(int captain);
	//스터디룸의 모든 멤버 삭제
	public abstract int deleteAllMember(int chatroomNum);
	//멤버번호로 매치테이블에서 조회
	public abstract List<Integer> selectMemberFromAllMatch(int memberNum);
	//멤버번호로 매치테이블에서 삭제
	public abstract int deleteMemberFromAllMatch(int memberNum);
}
