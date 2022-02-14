package com.mycompany.myapp.member.dto;

import java.util.List;
import java.util.Map;

import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;

public class MemberDtoContainSubjectAndRoomList extends MemberDTO{

	
	private List<MemberDtoContainStudyroom> studyroomList;

	public List<MemberDtoContainStudyroom> getStudyroomList() {
		return studyroomList;
	}

	public void setStudyroomList(List<MemberDtoContainStudyroom> studyroomList) {
		this.studyroomList = studyroomList;
	}
	
	
	
	
}
