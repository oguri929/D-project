package org.tester.chatting.room.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tester.chatting.room.domain.RoomVO;

@Repository
public class RoomDaoMybatis implements RoomDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public RoomDaoMybatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void setSqlMapClient(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Override
	public List<RoomVO> list(){
		return sqlSessionTemplate.selectList("list");
	}
	
	@Override
	public int delete(RoomVO roomVO) {
		return sqlSessionTemplate.delete("delete", roomVO);
	}
	
	@Override
	public int update(RoomVO roomVO) {
		return sqlSessionTemplate.update("update", roomVO);
	}
	
	@Override
	public void insert(RoomVO roomVO) {
		sqlSessionTemplate.insert("insert", roomVO);
	}
	
	@Override
	public RoomVO select(int code) {
		RoomVO vo = (RoomVO)sqlSessionTemplate.selectOne("select", code);
		return vo;
	}
}
