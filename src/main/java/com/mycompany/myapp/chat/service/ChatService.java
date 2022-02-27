package com.mycompany.myapp.chat.service;

import java.util.List;
import java.util.Map;

import com.mycompany.myapp.chat.domain.Chat;

public interface ChatService {
	public List<Chat> selectChatList(Map<String,Object> map);
	public List<Chat> selectFirtChatList(int roomNo);
	public int InsertChat(Chat chat);
	public Chat selectChat(Chat chat);
	public int deleteChattingBySr_no(int srNo);
}
