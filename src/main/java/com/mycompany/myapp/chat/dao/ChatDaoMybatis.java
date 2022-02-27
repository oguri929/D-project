package com.mycompany.myapp.chat.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.chat.domain.Chat;
import com.mycompany.myapp.chat.domain.ChatVO;

@Repository
public class ChatDaoMybatis implements ChatDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ChatDaoMybatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void setSqlMapClient(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void insert(ChatVO chatVO) {
		sqlSessionTemplate.insert("insert", chatVO);
	}

	@Override
	public List<Chat> selectChatList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chat> selectFirstChatList(int roomNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteChattingBySr_no(int srNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
