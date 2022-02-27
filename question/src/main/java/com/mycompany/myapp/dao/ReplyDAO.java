package com.mycompany.myapp.dao;

import java.util.List;
import com.mycompany.myapp.domain.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> readReply(int bno) throws Exception;
	
	public void writeReply(ReplyVO vo) throws Exception;
}
