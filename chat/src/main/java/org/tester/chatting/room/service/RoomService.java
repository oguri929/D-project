package org.tester.chatting.room.service;

import java.util.List;

import org.tester.chatting.room.domain.RoomVO;

public interface RoomService {
	public abstract List<RoomVO> list();
	
	public abstract int delete(RoomVO roomVO);
	
	public abstract int update(RoomVO roomVO);
	
	public abstract void insert(RoomVO roomVO);
}
