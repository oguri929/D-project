package com.mycompany.myapp.chat.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.mycompany.myapp.chat.domain.ChatRoomDTO;

@Repository
public class ChatRoomRepository {
	private Map<String,ChatRoomDTO> chatRoomDTOMap;
	
	
	private ChatRoomRepository() {//원래는 init 이여야함
		chatRoomDTOMap=new LinkedHashMap<>();
	}
	
	public List<ChatRoomDTO> findAllRooms(){
		List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
		Collections.reverse(result);
		return result;
	}
	public ChatRoomDTO findRoomById(String id) {
		return chatRoomDTOMap.get(id);
	}
	public ChatRoomDTO createChatRoomDTO(String name) {
		ChatRoomDTO room=ChatRoomDTO.create(name);
		chatRoomDTOMap.put(room.getRoomId(), room);
		
		return room;
	}
}
