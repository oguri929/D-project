package com.mycompany.myapp.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.ArticleVO;
import com.mycompany.myapp.domain.ReplyVO;

@Repository
public class ArticleDAOImpl implements ArticleDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void write(ArticleVO articleVO) throws Exception{
		sqlSession.insert("boardMapper.insert", articleVO);
	}
	
	@Override
	public List<ArticleVO> list() throws Exception{
		return sqlSession.selectList("boardMapper.list");
	}
	
	@Override
	public ArticleVO read(int bno) throws Exception {
		return sqlSession.selectOne("boardMapper.read", bno);
	}
	
	@Override
	public void update(ArticleVO articleVO) throws Exception {
		sqlSession.update("boardMapper.update", articleVO);
	}

	@Override
	public void delete(int bno) throws Exception {		
		sqlSession.delete("boardMapper.delete", bno);
	}
}
