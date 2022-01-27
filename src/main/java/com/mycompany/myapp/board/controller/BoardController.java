package com.mycompany.myapp.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.myapp.board.domain.BoardDto;
import com.mycompany.myapp.board.service.BoardService;
import com.mycompany.myapp.utils.Criteria;
import com.mycompany.myapp.utils.PageMaker;

@Controller("boardController")
public class BoardController {
	private static final String filePath="C:\\board\\file";
	
	@Inject
	private BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@RequestMapping(value="/board/write", method = RequestMethod.GET)
	public String writeBoard(Model model) {
		model.addAttribute("boardDto", new BoardDto());
		return "/board/write";
	}
	
	@RequestMapping(value="/board/write", method = RequestMethod.POST)
	public String writeBoard(BoardDto boardDto, MultipartHttpServletRequest mpRequest, 
							BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			return "/board/write";
		}
		boardService.writeBoard(boardDto, mpRequest);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/read/{num}")
	public String readBoard(Model model, @PathVariable int num) throws Exception {
		model.addAttribute("boardDto", boardService.readBoard(num));
		
		List<Map<String, Object>> fileList = boardService.selectFileList(num);
		model.addAttribute("fileList", fileList);
		return "/board/read";
	}
	
	@RequestMapping(value = "/board/list")
	public String listBoard( Model model, Criteria criteria) {
		List<BoardDto> boardList = boardService.listBoard(criteria);
		model.addAttribute("boardList", boardList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(boardService.countTotBoard());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "/board/list";
	}
	
	@RequestMapping(value="/board/edit/{num}", method = RequestMethod.GET)
	public String editBoard(Model model, @PathVariable int num) throws Exception {
		BoardDto boardDto = boardService.readBoard(num);
		model.addAttribute("boardDto", boardDto);
		
		List<Map<String, Object>> fileList = boardService.selectFileList(num);
		model.addAttribute("fileList", fileList);
		return "/board/edit";
	}
	
	@RequestMapping(value="/board/edit/{num}", method = RequestMethod.POST)
	public String editBoard(Model model, BoardDto boardDto, MultipartHttpServletRequest mpRequest,
							BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			return "/board/edit";
		}
		boardService.editBoard(boardDto, mpRequest);
		return "redirect:/board/list";

	}
	
	@RequestMapping(value="/board/delete/{num}", method = RequestMethod.GET)
	public String deleteBoard(Model model, @PathVariable int num) {
		model.addAttribute("num", num);
		return "/board/delete";
	}
	
	@RequestMapping(value="/board/delete", method = RequestMethod.POST)
	public String deleteBoard(int num) throws Exception {
		boardService.deleteFile(num);
		boardService.deleteBoard(num);	
		
		File fileDir = new File(filePath + "\\" + num);
		if(fileDir.exists()) {
			FileUtils.deleteDirectory(fileDir);
		}

		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/downFile/{FILE_NUM}")
	public void downFile(@RequestParam Map<String, Object> map, HttpServletResponse response,
						@PathVariable("FILE_NUM") int fileNum) throws Exception{
		Map<String, Object> resultMap = boardService.getFileInfoByFileNum(fileNum);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		int boardNum = Integer.parseInt(String.valueOf(resultMap.get("BOARD_NUM")));
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = FileUtils.readFileToByteArray(new File(filePath + "\\" + boardNum + "\\" +storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
	
	@RequestMapping(value="/board/delFile/{FILE_NUM}")
	public String delFile(@PathVariable("FILE_NUM") int fileNum, Model model) throws Exception {
		Map<String, Object> resultMap = boardService.getFileInfoByFileNum(fileNum);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		int boardNum = Integer.parseInt(String.valueOf(resultMap.get("BOARD_NUM")));
		
		File target = new File(filePath + "\\" + boardNum + "\\" + storedFileName);
		if(target.exists()) {
			target.delete();
		}
		boardService.deleteFile(boardNum);
		model.addAttribute("boardNum", boardNum);
		
		return "redirect:/board/edit/{boardNum}";
	}
}



