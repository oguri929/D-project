package com.mycompany.myapp.utils;

public class QuizSearchCriteria extends SearchCriteria {
	private int quizNum;
	private int chatroomNum;
	
	public int getQuizNum() {
		return quizNum;
	}
	public void setQuizNum(int quizNum) {
		this.quizNum = quizNum;
	}
	
	public int getChatroomNum() {
		return chatroomNum;
	}
	public void setChatroomNum(int chatroomNum) {
		this.chatroomNum = chatroomNum;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + getSearchType() + ", keyword=" + getKeyword() +
				", quizNum=" + quizNum +", chatroomNum=" + chatroomNum + "]";
	}
	
	
}
