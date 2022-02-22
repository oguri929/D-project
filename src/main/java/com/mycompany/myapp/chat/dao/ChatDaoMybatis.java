package com.mycompany.myapp.chat.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}