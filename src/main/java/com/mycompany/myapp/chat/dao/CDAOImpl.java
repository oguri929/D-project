package com.mycompany.myapp.chat.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.chat.domain.Chat;



@Repository
public class CDAOImpl implements CDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Chat> selectchatList(Map<String, Object> map) {
		return sqlSession.selectList("chat.selectChatList",map);
	}

	@Override
	public List<Chat> selectFirstChatList(int roomNo) {
		return sqlSession.selectList("chat.selectFirstChatList",roomNo);
	}

	@Override
	public int insertChat(Chat chat) {
		return sqlSession.insert("chat.insertChat",chat);
	}
}
