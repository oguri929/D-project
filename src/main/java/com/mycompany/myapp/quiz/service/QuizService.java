package com.mycompany.myapp.quiz.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.quiz.dao.QuizDao;
import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.QuizDtoForList;
import com.mycompany.myapp.quiz.dto.SolveQuizVo;
import com.mycompany.myapp.utils.QuizSearchCriteria;

@Service
public class QuizService implements QuizServiceInterface{
	private QuizDao dao;
	public QuizService(QuizDao dao){
		this.dao=dao;
	}
	
	@Override
	public void insertQuiz(List<QuizDto> dtos,long memberNum) {
		// TODO Auto-generated method stub
		for(QuizDto dto : dtos) {
			dto.setMakerNum(memberNum);
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
	public List<QuizDto> getQuizList(long subNum, long roomNum) {
		// TODO Auto-generated method stub
		return dao.selectQuizList(subNum,roomNum);
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
			int ranNum=ran.nextInt(quizList.size());
			quizNum.add(ranNum);
		}
		//출제 수 올리는 기능 추가
		Map<Integer,QuizDto> selectMap=new HashMap<Integer,QuizDto>();
		Iterator<Integer> iter=quizNum.iterator();
		int i=0;
		while(iter.hasNext()) {
			QuizDto quiz=quizList.get(iter.next());
			dao.countUPNOQ(quiz.getNum());
			selectMap.put(i, quiz);
			
			
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
		if(result) {
			dao.countUpNOA(quizDto.getNum());
		}
		return result;
	}

	@Override
	public List<QuizDto> getQuizListByMakerNum(long makerNum) {
		// TODO Auto-generated method stub
		
		return dao.selectQuizListByMakerNum(makerNum);
	}

	@Override
	public List<ChatRoomInfoOfMember> getChatroomListofMember(long memberNum) {
		// TODO Auto-generated method stub
		List<ChatRoomInfoOfMember> result=dao.selectAllChatroomOfMember(memberNum);
		return result;
	}

	@Override
	public int countTotQuiz(QuizSearchCriteria qscri) {
		// TODO Auto-generated method stub
		return dao.selectTotQuiz(qscri);
	}

	@Override
	public List<QuizDtoForList> getQuizListforLit(QuizSearchCriteria qscri,MemberDTO memberDto) {
		// TODO Auto-generated method stub
		List<QuizDtoForList> result=dao.selectAllQuiz(qscri);
		for(QuizDtoForList quizDto : result) {
			if (quizDto.getMakerNum()==memberDto.getNum()) {
				quizDto.setWriter(true);
			}
		}
		return result;
	}

	
	

}
