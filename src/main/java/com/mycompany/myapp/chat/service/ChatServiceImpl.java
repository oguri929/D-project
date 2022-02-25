package com.mycompany.myapp.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.chat.dao.ChatDAOImpl;
import com.mycompany.myapp.chat.domain.Chat;


@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatDAOImpl chatDAO;
	@Override
	public List<Chat> selectChatList(Map<String,Object> map){
		List<Chat> list=chatDAO.selectChatList(map);
		return list;
	}
	@Override
	public List<Chat> selectFirtChatList(int roomNo){
		List<Chat> result=chatDAO.selectFirstChatList(roomNo);
//		for(Chat chat:result) {
//			
//		}
		return result;
	}
	@Override
	public int InsertChat(Chat chat) {
		return chatDAO.insertChat(chat);
	}
	public Chat selectChat(Chat chat) {
		return chatDAO.selectChat(chat);
	}
}
