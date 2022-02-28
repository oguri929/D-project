package com.mycompany.myapp.qnaboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.qnaboard.domain.ReplyVO;



@Repository
public class ReplyDAOImpl implements ReplyDAO{
	@Inject
	private SqlSession sql;
	
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception{
		return sql.selectList("boardMapper.readReply", bno);
	}
	
	@Override
	public void writeReply(ReplyVO vo) throws Exception{
		sql.insert("boardMapper.writeReply", vo);
	}
}
