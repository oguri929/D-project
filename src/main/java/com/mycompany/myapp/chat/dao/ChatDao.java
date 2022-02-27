package com.mycompany.myapp.chat.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.mycompany.myapp.chat.domain.Chat;
import com.mycompany.myapp.chat.domain.ChatVO;

public interface ChatDao {

	
	public void setSqlMapClient(SqlSessionTemplate sqlSessionTemplate);
	
	public List<Chat> selectChatList(Map<String,Object>map);
	
	public List<Chat> selectFirstChatList(int roomNo);
	
	public void insert(ChatVO chatVO) ;
	
	public int deleteChattingBySr_no(int srNo);


}