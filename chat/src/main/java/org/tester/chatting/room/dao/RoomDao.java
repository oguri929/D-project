package org.tester.chatting.room.dao;

import java.util.List;

import org.tester.chatting.room.domain.RoomVO;

public interface RoomDao {
	public abstract List<RoomVO> list();
	
	public abstract int delete(RoomVO roomVO);
	
	public abstract int update(RoomVO roomVO);
	
	public abstract void insert(RoomVO roomVO);
	
	public abstract RoomVO select(int code);
}
