package com.mycompany.myapp.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.board.domain.BoardDto;
import com.mycompany.myapp.utils.Criteria;

@Repository
public class BoardDaoMyBatis implements BoardDao{
	private SqlSessionTemplate sqlSessionTemplate;

	public BoardDaoMyBatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void insertBoard(BoardDto boardDto) {
		sqlSessionTemplate.insert("insertBoard", boardDto);
	}

	@Override
	public BoardDto selectBoardOne(int num) {
		 BoardDto boardDto = (BoardDto)sqlSessionTemplate.selectOne("selectBoardOne", num);
		 return boardDto;
	}

	@Override
	public List<BoardDto> selectAllBoard(Criteria criteria) {
		return sqlSessionTemplate.selectList("selectAllBoard", criteria);
	}

	@Override
	public int updateBoard(BoardDto boardDto) {
		return sqlSessionTemplate.update("updateBoard", boardDto);
	}

	@Override
	public int updateBoardCnt(int num) {
		return sqlSessionTemplate.update("updateBoardCnt", num);
	}

	@Override
	public int deleteBoard(int num) {
		return sqlSessionTemplate.delete("deleteBoard", num);
	}

	@Override
	public int selectTotBoard() {
		return sqlSessionTemplate.selectOne("selectTotBoard");
	}

	@Override
	public void insertFile(Map<String, Object> map) {
		sqlSessionTemplate.insert("insertFile", map);
		
	}

	@Override
	public List<Map<String, Object>> selectFileList(int num) throws Exception {
		return sqlSessionTemplate.selectList("selectFileList", num);
	}

	@Override
	public Map<String, Object> selectFileInfoByFileNum(int fileNum) throws Exception {
		return sqlSessionTemplate.selectOne("selectFileInfoByFileNum", fileNum);
	}

	@Override
	public int deleteFile(int num) {
		return sqlSessionTemplate.delete("deleteFile", num);
	}
	
	
	
	
	

	
	
}
