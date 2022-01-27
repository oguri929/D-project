package com.mycompany.myapp.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.myapp.board.domain.BoardDto;
import com.mycompany.myapp.utils.Criteria;

public interface BoardService {
	//게시글 작성
	public abstract void writeBoard(BoardDto boardDto, MultipartHttpServletRequest mpRequest) throws Exception;
	//게시글 상세보기
	public abstract BoardDto readBoard(int num);
	//게시글 리스트보기
	public abstract List<BoardDto> listBoard(Criteria criteria);
	//게시글 수정하기
	public abstract int editBoard(BoardDto boardDto, MultipartHttpServletRequest mpRequest) throws Exception;
	//게시글 삭제하기
	public abstract int deleteBoard(int num);
	//게시글 정보가져오기
	public abstract BoardDto getBoardInfo(int num);
	//전체 게시글 개수 가져오기
	public abstract int countTotBoard();
	//첨부파일 조회
	public abstract List<Map<String, Object>> selectFileList(int num) throws Exception;
	//파일번호로 파일정보 조회(파일 다운로드에 사용)
	public abstract Map<String, Object> getFileInfoByFileNum(int fileNum) throws Exception;
	//첨부파일 삭제
	public abstract int deleteFile(int num);
}
