package com.mycompany.myapp.quiz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.dto.QuizDto;

@Repository
public interface QuizDaoInterface {
	public void insertQuiz(QuizDto dto);
	public void updateQuiz(QuizDto dto);
	public List<QuizDto> selectQuizList(long subjectNum);
	public List<QuizDto> selectQuizListByMakerNum(long makertNum);
	//public QuizDto selectOneQuiz(QuizDto dto);
	public List<QuizDto> searchQuiz(String word);
	public void deleteQuiz(long num);
	public QuizDto selectOneQuizByNum(long num);
	public void countUpNOA(long num);
	public void sountUPNOQ(long num);
	public List<ChatRoomInfoOfMember> selectAllChatroomOfMember(long num);
	
	
}