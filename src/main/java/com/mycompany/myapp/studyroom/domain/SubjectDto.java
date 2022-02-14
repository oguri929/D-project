package com.mycompany.myapp.studyroom.domain;

import org.apache.ibatis.type.Alias;

@Alias("subjectDto")
public class SubjectDto {
	private int subjectNum;
	private String subject;

	public SubjectDto() {}

	public SubjectDto(int subjectNum, String subject) {
		super();
		this.subjectNum = subjectNum;
		this.subject = subject;
	}

	public int getSubjectNum() {
		return subjectNum;
	}

	public void setSubjectNum(int subjectNum) {
		this.subjectNum = subjectNum;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "SubjectDto [subjectNum=" + subjectNum + ", subject=" + subject + "]";
	}

	
	
	
}
