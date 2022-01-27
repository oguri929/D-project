package com.mycompany.myapp.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.myapp.board.dao.BoardDao;
import com.mycompany.myapp.board.domain.BoardDto;
import com.mycompany.myapp.utils.Criteria;
import com.mycompany.myapp.utils.FileManager;

@Service
public class BoardServiceImpl implements BoardService{
	private FileManager fileManager;

	private BoardDao boardDao;

	public BoardServiceImpl(FileManager fileManager, BoardDao boardDao) {
		super();
		this.fileManager = fileManager;
		this.boardDao = boardDao;
	}

	@Override
	public void writeBoard(BoardDto boardDto, MultipartHttpServletRequest mpRequest) throws Exception {
		boardDao.insertBoard(boardDto);
		
		MultipartFile file = mpRequest.getFile("file");
		if(!file.isEmpty()) {			
			List<Map<String,Object>> list = fileManager.parseInsertFileInfo(boardDto, mpRequest);
			//여러개의 첨부파일을 등록하기위해서
			int size = list.size();
			for(int i=0; i<size; i++) {
				boardDao.insertFile(list.get(i));
			}
		}
	}

	@Override
	public BoardDto readBoard(int num) {
		boardDao.updateBoardCnt(num);
		return boardDao.selectBoardOne(num);
	}

	@Override
	public List<BoardDto> listBoard(Criteria criteria) {	
		return boardDao.selectAllBoard(criteria);
	}

	@Override
	public int editBoard(BoardDto boardDto, MultipartHttpServletRequest mpRequest) throws Exception {
		int result = boardDao.updateBoard(boardDto);
		
		MultipartFile file = mpRequest.getFile("file");
		if(!file.isEmpty()) {			
			List<Map<String,Object>> list = fileManager.parseInsertFileInfo(boardDto, mpRequest);
			//여러개의 첨부파일을 등록하기위해서
			int size = list.size();
			for(int i=0; i<size; i++) {
				boardDao.insertFile(list.get(i));
			}
		}
		return result;
	}

	@Override
	public int deleteBoard(int num) {
		return boardDao.deleteBoard(num);
	}

	@Override
	public BoardDto getBoardInfo(int num) {
		return boardDao.selectBoardOne(num);
	}

	@Override
	public int countTotBoard() {
		return boardDao.selectTotBoard();
	}

	@Override
	public List<Map<String, Object>> selectFileList(int num) throws Exception {
		return boardDao.selectFileList(num);
	}

	@Override
	public Map<String, Object> getFileInfoByFileNum(int fileNum) throws Exception {
		return boardDao.selectFileInfoByFileNum(fileNum);
	}

	@Override
	public int deleteFile(int num) {
		return boardDao.deleteFile(num);
	}
	
	
	
	
}
