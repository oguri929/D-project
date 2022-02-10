package com.mycompany.myapp.studyroom.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("studyroomDto")
public class StudyroomDto {
	private int num;
	private String pw;
	private String local;
	private String roomName;
	private String roomDiscript;
	private int memberLimit;
	private Date regdate;
	private int captain;
	private int cnt;
	private int subjectNum;
	private SubjectDto subjectDto;

	public StudyroomDto() {}

	public StudyroomDto(String pw, String local, String roomName, String roomDiscript, int memberLimit, int captain,
			int subjectNum) {
		super();
		this.pw = pw;
		this.local = local;
		this.roomName = roomName;
		this.roomDiscript = roomDiscript;
		this.memberLimit = memberLimit;
		this.captain = captain;
		this.subjectNum = subjectNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomDiscript() {
		return roomDiscript;
	}

	public void setRoomDiscript(String roomDiscript) {
		this.roomDiscript = roomDiscript;
	}

	public int getMemberLimit() {
		return memberLimit;
	}

	public void setMemberLimit(int memberLimit) {
		this.memberLimit = memberLimit;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public int getCaptain() {
		return captain;
	}

	public void setCaptain(int captain) {
		this.captain = captain;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getSubjectNum() {
		return subjectNum;
	}

	public void setSubjectNum(int subjectNum) {
		this.subjectNum = subjectNum;
	}

	public SubjectDto getSubjectDto() {
		return subjectDto;
	}

	public void setSubjectDto(SubjectDto subjectDto) {
		this.subjectDto = subjectDto;
	}

}
