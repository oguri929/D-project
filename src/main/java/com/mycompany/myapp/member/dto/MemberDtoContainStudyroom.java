package com.mycompany.myapp.member.dto;

import com.mycompany.myapp.studyroom.domain.StudyroomDto;

public class MemberDtoContainStudyroom extends StudyroomDto{
	private String subject;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	

}
