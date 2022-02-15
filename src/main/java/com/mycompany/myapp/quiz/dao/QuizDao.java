package com.mycompany.myapp.quiz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.QuizDtoForList;
import com.mycompany.myapp.utils.QuizSearchCriteria;

@Repository
public class QuizDao implements QuizDaoInterface {
	public SqlSessionTemplate sqlSessiontemplate;
	@Autowired
	public QuizDao(SqlSessionTemplate sqlSessiontemplate) {
		this.sqlSessiontemplate=sqlSessiontemplate;
	}
	@Override
	public void insertQuiz(QuizDto dto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.insert("insertQuiz",dto);
		
	}
/*
	@Override
	public QuizDto selectOneQuiz(QuizDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public List<QuizDto> searchQuiz(String word) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("searchQuiz","%"+word+"%");
	}

	@Override
	public void deleteQuiz(long num) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("deleteQuiz",num);
	}

	@Override
	public QuizDto selectOneQuizByNum(long num) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("selectOneQuizByNum",num);
	}
	@Override
	public List<ChatRoomInfoOfMember> selectAllChatroomOfMember(long memberNum) {
		return sqlSessiontemplate.selectList(NAMESPACE+"getChatRoomNames", memberNum);
	}
	@Override
	public void countUpNOA(long num) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("countUpNOA",num);
	}

	@Override
	public void countUPNOQ(long num) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("countUpNOQ",num);
		
	}

	@Override
	public void updateQuiz(QuizDto dto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("updateQuiz",dto);
	}
	@Override
	public List<QuizDto> selectQuizList(long subjectNum) {
		// TODO Auto-generated method stub
		
		return sqlSessiontemplate.selectList("selectQuizListBySubjectNum",subjectNum);
	}
	@Override
	public List<QuizDto> selectQuizListByMakerNum(long makerNum) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("selectQuizListByMakerNum",makerNum);
	}
	@Override
	public int selectTotQuiz(QuizSearchCriteria qscri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("selectTotQuiz",qscri);
	}
	@Override
	public List<QuizDtoForList> selectAllQuiz(QuizSearchCriteria qscri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("selectAllQuiz",qscri);
	}


}
