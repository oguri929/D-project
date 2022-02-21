package com.mycompany.myapp.quiz.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.QuizDtoForList;
import com.mycompany.myapp.quiz.dto.SolveQuizVo;
import com.mycompany.myapp.utils.QuizSearchCriteria;

@Service
public interface QuizServiceInterface {
	public void insertQuiz(List<QuizDto> dto,long memberNum);
	public void updateQuiz(QuizDto dto);
	public List<QuizDto> searchQuiz(String word);
	public void deletQuiz(QuizDto dto);
	public QuizDto getQuizOne(long num);
	public List<QuizDto> getQuizList(long num);
	public List<QuizDto> getQuizList(long subNum,long roomNum);
	public List<QuizDto> getQuizListByMakerNum(long makerNum);
	public Map<Integer,QuizDto> selectQuizList(List<QuizDto> quizList, int numOfQuestion );
	public boolean checkAnswer(SolveQuizVo solveVo,QuizDto quizDto);
	public List<ChatRoomInfoOfMember> getChatroomListofMember(long memberNum);
	public abstract int countTotQuiz(QuizSearchCriteria qscri);
	public abstract List<QuizDtoForList> getQuizListforLit(QuizSearchCriteria qscri,MemberDTO memberDto);
}
	
