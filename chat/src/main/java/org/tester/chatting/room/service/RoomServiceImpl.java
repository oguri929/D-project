package org.tester.chatting.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tester.chatting.room.dao.RoomDao;
import org.tester.chatting.room.domain.RoomVO;

@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomDao roomDao;

	public RoomDao getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	@Override
	public List<RoomVO> list(){
		return roomDao.list();
	}
	
	@Override
	public int delete(RoomVO roomVO) {
		return roomDao.delete(roomVO);
	}
	
	@Override
	public int update(RoomVO roomVO) {
		return roomDao.update(roomVO);
	}
	
	@Override
	public void insert(RoomVO roomVO) {
		roomDao.insert(roomVO); 
	}
}
