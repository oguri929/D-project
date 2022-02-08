package com.mycompany.myapp.quiz.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.SolveQuizVo;

@Service
public interface QuizServiceInterface {
	public void insertQuiz(List<QuizDto> dto);
	public void updateQuiz(QuizDto dto);
	public List<QuizDto> searchQuiz(String word);
	public void deletQuiz(QuizDto dto);
	public QuizDto getQuizOne(long num);
	public List<QuizDto> getQuizList(long num);
	public List<QuizDto> getQuizListByMakerNum(long makerNum);
	public Map<Integer,QuizDto> selectQuizList(List<QuizDto> quizList, int numOfQuestion );
	public boolean checkAnswer(SolveQuizVo solveVo,QuizDto quizDto);
	
	
}
