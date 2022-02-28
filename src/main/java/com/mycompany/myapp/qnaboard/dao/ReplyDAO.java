package com.mycompany.myapp.qnaboard.dao;

import java.util.List;

import com.mycompany.myapp.qnaboard.domain.ReplyVO;


public interface ReplyDAO {
	public List<ReplyVO> readReply(int bno) throws Exception;
	
	public void writeReply(ReplyVO vo) throws Exception;
}
