package com.mycompany.myapp.qnaboard.service;

import java.util.List;

import com.mycompany.myapp.qnaboard.domain.ReplyVO;


public interface ReplyService {
	public List<ReplyVO> readReply(int bno) throws Exception;
	
	public void writeReply(ReplyVO vo) throws Exception;
}
