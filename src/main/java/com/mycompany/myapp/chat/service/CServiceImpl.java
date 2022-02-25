package com.mycompany.myapp.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.chat.dao.CDAO;
import com.mycompany.myapp.chat.domain.Chat;

@Service
public class CServiceImpl implements CService{
	
	@Autowired
	private CDAO chatDAO;
	
	@Override
	public List<Chat> selectChatList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Chat> list = chatDAO.selectchatList(map);
//		log.info("daoList={}",list);
		return list;
	}

	@Override
	public List<Chat> selectFirstChatList(int roomNo) {
		
		return chatDAO.selectFirstChatList(roomNo);
	}

	@Override
	public int insertChat(Chat chat) {
		return chatDAO.insertChat(chat);
	}

}
