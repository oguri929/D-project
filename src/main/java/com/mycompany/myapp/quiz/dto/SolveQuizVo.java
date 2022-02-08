package com.mycompany.myapp.quiz.dto;

import java.util.List;

public class SolveQuizVo {
	private long num;
	private long subjectNum;
	private long quizType;
	private String question;
	private String answer;
	private String answerTrue;//나중에 문제 세션 저장시 삭제도 가능함
	private List<SolveQuizVo> answerList;
	private boolean checkAnswer;
	
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public long getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(long subjectNum) {
		this.subjectNum = subjectNum;
	}
	public long getQuizType() {
		return quizType;
	}
	public void setQuizType(long quizType) {
		this.quizType = quizType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public List<SolveQuizVo> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<SolveQuizVo> answerList) {
		this.answerList = answerList;
	}
	public boolean isCheckAnswer() {
		return checkAnswer;
	}
	public void setCheckAnswer(boolean checkAnswer) {
		this.checkAnswer = checkAnswer;
	}
	public String getAnswerTrue() {
		return answerTrue;
	}
	public void setAnswerTrue(String answerTure) {
		this.answerTrue = answerTure;
	}
	
	
	
}
