package com.mycompany.myapp.quiz.dto;

public class QuizDtoForList extends QuizDto{
	private String roomName;
	private String nickName;
	private String subjectName;
	private long correctRate;
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getnickName() {
		return nickName;
	}
	public void setnickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public long getCorrectRate() {
		return correctRate;
	}
	public void setCorrectRate() {
		this.correctRate = getNumOfAnswser()/getNumOfQuiz();
	}
	@Override
	public String toString() {
		return "QuizDtoForList: "+
				"makerNickName: ["+nickName+"]"+
				"subjectName: ["+subjectName+"]"+
				"chatroomName: ["+roomName+"]"
				;
	}
	
	
	
}
