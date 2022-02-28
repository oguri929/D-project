package com.mycompany.myapp.qnaboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.qnaboard.dao.ArticleDAO;
import com.mycompany.myapp.qnaboard.domain.ArticleVO;



@Service
public class ArticleServiceImpl implements ArticleService{
	@Inject
	private ArticleDAO dao;
	
	@Override
	public void write(ArticleVO articleVO) throws Exception{
		dao.write(articleVO);
	}
	
	@Override
	public List<ArticleVO> list() throws Exception{
		return dao.list();
	}

	@Override
	public ArticleVO read(int bno) throws Exception {
		return dao.read(bno);
	}
	
	@Override
	public void update(ArticleVO articleVO) throws Exception {
		dao.update(articleVO);
	}
	
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}
}
