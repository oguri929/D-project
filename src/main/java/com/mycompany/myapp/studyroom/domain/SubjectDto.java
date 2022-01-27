package com.mycompany.myapp.studyroom.domain;

import org.apache.ibatis.type.Alias;

@Alias("subjectDto")
public class SubjectDto {
	private int num;
	private String subject;
	
	
	
	public SubjectDto() {}

	public SubjectDto(int num, String subject) {
		super();
		this.num = num;
		this.subject = subject;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "SubjectDto [num=" + num + ", subject=" + subject + "]";
	}
	
	
}
