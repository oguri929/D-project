package com.mycompany.myapp.quiz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.QuizDtoForList;
import com.mycompany.myapp.utils.QuizSearchCriteria;

@Repository
public interface QuizDaoInterface {
	String NAMESPACE = "quiz.";
	public void insertQuiz(QuizDto dto);
	public void updateQuiz(QuizDto dto);
	public List<QuizDto> selectQuizList(long subjectNum);
	public List<QuizDto> selectQuizListByMakerNum(long makerNum);
	//public QuizDto selectOneQuiz(QuizDto dto);
	public List<QuizDto> searchQuiz(String word);
	public void deleteQuiz(long num);
	public QuizDto selectOneQuizByNum(long num);
	public void countUpNOA(long num);
	public void countUPNOQ(long num);
	public List<ChatRoomInfoOfMember> selectAllChatroomOfMember(long num);
	public abstract int selectTotQuiz(QuizSearchCriteria qscri);
	public abstract List<QuizDtoForList> selectAllQuiz(QuizSearchCriteria qscri); 
	
	
}