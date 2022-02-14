package com.mycompany.myapp.quiz.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("quizDto")
public class QuizDto {
	
	private long num;
	private long chatroomNum;
	private long makerNum;
	private long subjectNum;
	private String sSubjectNum;
//	private List<String> sSubjectNum;
//	
//	public List<String> getsSubjectNum() {
//		return sSubjectNum;
//	}
//	public void setsSbjectNum(List<String> sSubjectNum) {
//		this.sSubjectNum = sSubjectNum;
//	}
	private long quizType;

	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String answer;
	private String explanation;
	private long numOfQuiz;
	private long numOfAnswser;
	private String subject;
	//private String //방번호
	//private String 
	private List<QuizDto> quizList;
	
	public List<QuizDto> getQuizList() {
		return quizList;
	}
	public void setQuizList(List<QuizDto> quizList) {
		this.quizList = quizList;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public void setSubjectNum(String num) {
		this.subjectNum=Long.parseLong(num);
	}
	public String getsSubjectNum() {
		return sSubjectNum;
	}
	public void setsSubjectNum(String sSubjectNum) {
		String[] tem=sSubjectNum.split(",");
		this.subjectNum = Long.parseLong(tem[0]);
		this.chatroomNum=Long.parseLong(tem[1]);
		
//		this.subjectNum=Long.parseLong(sSubjectNum);
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public long getChatroomNum() {
		return chatroomNum;
	}
	public void setChatroomNum(long roomNum) {
		this.chatroomNum = roomNum;
	}
	public long getMakerNum() {
		return makerNum;
	}
	public void setMakerNum(long makerNum) {
		this.makerNum = makerNum;
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
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getOption5() {
		return option5;
	}
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	public long getNumOfQuiz() {
		return numOfQuiz;
	}
	public void setNumOfQuiz(long numOfQuiz) {
		this.numOfQuiz = numOfQuiz;
	}
	public long getNumOfAnswser() {
		return numOfAnswser;
	}
	public void setNumOfAnswser(long numOfAnswser) {
		this.numOfAnswser = numOfAnswser;
	}
	@Override
	public String toString() {
		return "question: "+this.quizType+" subjectNum: "+this.subjectNum+" sSubjectNum : "+this.sSubjectNum;
	}
	
}
