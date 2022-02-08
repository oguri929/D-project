package com.mycompany.myapp.quiz.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.quiz.dao.QuizDao;
import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.SolveQuizVo;

@Service
public class QuizService implements QuizServiceInterface{
	private QuizDao dao;
	public QuizService(QuizDao dao){
		this.dao=dao;
	}
	
	@Override
	public void insertQuiz(List<QuizDto> dtos) {
		// TODO Auto-generated method stub
		System.out.println("service: "+dtos);
		for(QuizDto dto : dtos) {
			System.out.println("servce: "+dto);
			System.out.println("service: "+dto.getQuestion());
			dao.insertQuiz(dto);
		}
		
	}

	@Override
	public void updateQuiz(QuizDto dto) {
		// TODO Auto-generated method stub
		dao.updateQuiz(dto);
		
	}

	@Override
	public List<QuizDto> searchQuiz(String word) {
		// TODO Auto-generated method stub
		dao.searchQuiz(word);
		return null;
	}

	@Override
	public void deletQuiz(QuizDto dto) {
		// TODO Auto-generated method stub
		dao.deleteQuiz(dto.getNum());
		
	}

	@Override
	public QuizDto getQuizOne(long num) {
		// TODO Auto-generated method stub
		
		return dao.selectOneQuizByNum(num);
	}

	@Override
	public List<QuizDto> getQuizList(long num) {
		// TODO Auto-generated method stub
		
		return dao.selectQuizList(num);
	}

	@Override
	public Map<Integer,QuizDto> selectQuizList(List<QuizDto> quizList, int numOfQuestion) {
		// TODO Auto-generated method stub
		if(numOfQuestion>quizList.size()) {
			numOfQuestion=quizList.size();
		}
		HashSet<Integer> quizNum=new HashSet<Integer>();
		Random ran = new Random();
		while(quizNum.size()<numOfQuestion) {
			int ranNum=ran.nextInt(quizList.size()-1);
			quizNum.add(ranNum);
			System.out.println(ranNum);
		}
		//출제 수 올리는 기능 추가
		Map<Integer,QuizDto> selectMap=new HashMap<Integer,QuizDto>();
		Iterator<Integer> iter=quizNum.iterator();
		int i=0;
		while(iter.hasNext()) {
			selectMap.put(i, quizList.get(iter.next()));
			
			i+=1;
		}
		return selectMap;
	}

	@Override
	public boolean checkAnswer(SolveQuizVo solveVo, QuizDto quizDto) {
		// TODO Auto-generated method stub
		boolean result=false;
		int cntAnswer=0;
		solveVo.setAnswerTrue(quizDto.getAnswer());
		String[] answer=quizDto.getAnswer().split(",");
		if (quizDto.getQuizType()==3) {
			for(int i=0;i<answer.length;i++) {
				if (solveVo.getAnswer().contains(answer[i])) {
					cntAnswer+=1;
				}
			}
			if (cntAnswer==answer.length) {
				result=true;
			}
		}else {
			if(solveVo.getAnswer().equals(quizDto.getAnswer())) {
				result=true;
			}
		}
		return result;
	}

	@Override
	public List<QuizDto> getQuizListByMakerNum(long makerNum) {
		// TODO Auto-generated method stub
		
		return dao.selectQuizListByMakerNum(makerNum);
	}
	

}
