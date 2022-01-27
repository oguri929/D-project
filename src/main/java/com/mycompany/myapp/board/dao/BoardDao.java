package com.mycompany.myapp.board.dao;

import java.util.List;
import java.util.Map;

import com.mycompany.myapp.board.domain.BoardDto;
import com.mycompany.myapp.utils.Criteria;

public interface BoardDao {
	//게시글 삽입
	public abstract void insertBoard(BoardDto boardDto);
	//게시글 한 개 조회
	public abstract BoardDto selectBoardOne(int num);
	//게시글 여러개 조회
	public abstract List<BoardDto> selectAllBoard(Criteria criteria);
	//게시글 수정
	public abstract int updateBoard(BoardDto boardDto);
	//조회수 수정
	public abstract int updateBoardCnt(int num);
	//게시글 삭제
	public abstract int deleteBoard(int num);
	//총 게시글 수 조회
	public abstract int selectTotBoard();
	//첨부파일 업로드
	public abstract void insertFile(Map<String,Object> map);
	//첨부파일 조회
	public abstract List<Map<String, Object>> selectFileList(int num) throws Exception;
	//파일 번호로 파일내용 조회(파일 다운로드에 사용)
	public abstract Map<String, Object> selectFileInfoByFileNum(int fileNum) throws Exception;
	//첨부파일 삭제
	public abstract int deleteFile(int num);
}
