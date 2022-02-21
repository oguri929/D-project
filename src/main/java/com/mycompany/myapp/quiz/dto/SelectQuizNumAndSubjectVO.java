package com.mycompany.myapp.quiz.dto;

public class SelectQuizNumAndSubjectVO {
	private long subjectNum;
	private int roomNum;
	private int numOfQuestion;
	public long getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(long subjectNum) {
		this.subjectNum = subjectNum;
	}
	public int getNumOfQuestion() {
		return numOfQuestion;
	}
	public void setNumOfQuestion(int numOfQueztion) {
		this.numOfQuestion = numOfQueztion;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	
}
