package com.mycompany.myapp.quiz.dto;

import org.apache.ibatis.type.Alias;

@Alias("chatRoomInfoOfMember")
public class ChatRoomInfoOfMember {
	private long chatroomNum;
	private String roomName;
	private String subject;
	private long subjectNum;
	
	public long getChatroomNum() {
		return chatroomNum;
	}
	public void setChatroomNum(long chatroomNum) {
		this.chatroomNum = chatroomNum;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public long getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(long subjectNum) {
		this.subjectNum = subjectNum;
	}

	
	
}
