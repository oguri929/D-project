package com.mycompany.myapp.chat.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mycompany.myapp.chat.domain.ChatMessageDTO;
@Controller

public class StompSocketController {
	@Autowired
	private final SimpMessagingTemplate template;
	
	public StompSocketController(SimpMessagingTemplate template) {
		this.template=template;
	}
	
	@MessageMapping("/chat/enter")
	public void enter(ChatMessageDTO message) {
		message.setMessage(message.getWriter()+"님이 채팅방에 참여하였습니다.");
		template.convertAndSend("/sub/chat/room"+message.getRoomId(),message);
		
	}

}
