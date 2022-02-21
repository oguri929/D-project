package com.mycompany.myapp.chat.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.mycompany.myapp.member.dto.MemberDTO;

@Component
public class HandlerChat extends TextWebSocketHandler{
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<WebSocketSession,MemberDTO> sessionMap=new HashMap<WebSocketSession,MemberDTO>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		// 채팅방에 접속한 사용자 세션을 리스트에 저장
		sessionList.add(session);
		Map<String,Object> sessionVal=session.getAttributes();
		sessionMap.put(session, (MemberDTO)sessionVal.get("user"));
		String userId=(String)(sessionMap.get(session)).getNickname();
		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(userId + "님이 입장 했습니다."));
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String userId=sessionMap.get(session).getNickname();
		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(userId + " : " + message.getPayload()));
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String userId=sessionMap.get(session).getNickname();
		// 채팅방에서 퇴장한 사용자 세션을 리스트에서 제거
		sessionList.remove(session);
		sessionMap.remove(session);
		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(userId + "님이 퇴장 했습니다."));
		}

	}
}
