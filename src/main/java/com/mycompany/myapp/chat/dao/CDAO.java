package com.mycompany.myapp.chat.dao;

import java.util.List;
import java.util.Map;

import com.mycompany.myapp.chat.domain.Chat;

public interface CDAO {
	List<Chat> selectchatList(Map<String, Object> map);

	List<Chat> selectFirstChatList(int roomNo);

	int insertChat(Chat chat);

}
