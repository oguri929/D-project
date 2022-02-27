package com.mycompany.myapp.chat.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.chat.domain.Chat;
import com.mycompany.myapp.chat.domain.ChatVO;



@Repository
public class ChatDAOImpl implements ChatDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public List<Chat> selectChatList(Map<String,Object>map){
		return sqlSession.selectList("chat.selectChatList",map);
	}
	
	public List<Chat> selectFirstChatList(int roomNo){
		return sqlSession.selectList("chat.selectFirstChatList",roomNo);
	}
	public int insertChat(Chat chat) {
		return sqlSession.insert("chat.insertChat",chat);
	}
	public Chat selectChat(Chat chat) {
		return sqlSession.selectOne("chat.selectChat",chat);
	}

	@Override
	public void setSqlMapClient(SqlSessionTemplate sqlSessionTemplate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(ChatVO chatVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteChattingBySr_no(int srNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("chat.deleteChattingBySr_no", srNo);
	}
	
}
