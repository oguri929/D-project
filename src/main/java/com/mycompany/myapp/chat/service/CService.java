package com.mycompany.myapp.chat.service;

import java.util.List;
import java.util.Map;

import com.mycompany.myapp.chat.domain.Chat;

public interface CService {
	List<Chat> selectChatList(Map<String, Object> map);

	List<Chat> selectFirstChatList(int roomNo);

	int insertChat(Chat chat);

}
