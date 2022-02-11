package org.tester.chatting.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tester.chatting.chat.dao.ChatDao;
import org.tester.chatting.chat.domain.ChatVO;

@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatDao chatDao;

	public ChatDao getChatDao() {
		return chatDao;
	}

	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}
	
	@Override
	public void insert(ChatVO chatVO) {
		chatDao.insert(chatVO); 
	}
}
