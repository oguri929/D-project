package com.mycompany.myapp.chat.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;


public class ChatRoomDTO {
	private String roomId;
	private String name;
	private Set<WebSocketSession> session=new HashSet<>();
	
	public Set<WebSocketSession> getSession() {
		return session;
	}

	public void setSession(Set<WebSocketSession> session) {
		this.session = session;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ChatRoomDTO create(String name) {
		ChatRoomDTO room=new ChatRoomDTO();
		
		room.roomId=UUID.randomUUID().toString();
		room.name=name;
		return room;
	}
}
