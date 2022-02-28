package com.mycompany.myapp.qnaboard.service;

import java.util.List;

import com.mycompany.myapp.qnaboard.domain.ArticleVO;

public interface ArticleService {
	// 게시글 작성
	public void write(ArticleVO articleVO) throws Exception;
	
	// 목록 조회
	public List<ArticleVO> list() throws Exception;
	
	//조회
	public ArticleVO read(int bno) throws Exception;
	
	// 게시물 수정
	public void update(ArticleVO articleVO) throws Exception;
		
	//삭제
	public void delete(int bno) throws Exception;
}
