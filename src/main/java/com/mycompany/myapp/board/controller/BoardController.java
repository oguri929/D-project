package com.mycompany.myapp.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.myapp.board.domain.BoardDto;
import com.mycompany.myapp.board.service.BoardService;
import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.service.MemberService;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.utils.Criteria;
import com.mycompany.myapp.utils.PageMaker;

@Controller("boardController")
public class BoardController {
	private static final String filePath="C:\\board\\file";
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private BoardService boardService;
	private MemberService memberService;
	
	@Autowired
	public BoardController(BoardService boardService, MemberService memberService) {
		super();
		this.boardService = boardService;
		this.memberService = memberService;
	}

	@RequestMapping(value="/board/write", method = RequestMethod.GET)
	public String writeBoard(Model model) {
		model.addAttribute("boardDto", new BoardDto());
		return "/board/write";
	}
	
	@RequestMapping(value="/board/write", method = RequestMethod.POST)
	// binding한 결과가 result에 담긴다.
	public String writeBoard(@ModelAttribute @Valid BoardDto boardDto, BindingResult bindingResult,
							MultipartHttpServletRequest mpRequest) throws Exception {
		logger.debug("board : {}", boardDto);
		
		//에러가 있는지 검사
		if(bindingResult.hasErrors()) {
			logger.debug("Binding Result has error!");
			//에러를 List로 저장
			List<ObjectError> list = bindingResult.getAllErrors();
			for( ObjectError error : list ) {
				System.out.println(error);
			}
			return "/board/write";
		}
		boardService.writeBoard(boardDto, mpRequest);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/read/{num}")
	public String readBoard(Model model, @PathVariable int num) throws Exception {
		BoardDto boardDto = boardService.readBoard(num);
		MemberDTO memberDto = memberService.selectMemberByNum(boardDto.getWriter());
		boardDto.setMemberDto(memberDto);
		model.addAttribute("boardDto", boardDto);

		List<Map<String, Object>> fileList = boardService.selectFileList(num);		
		model.addAttribute("fileList", fileList);
		return "/board/read";
	}
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String listBoard( Model model, Criteria criteria) throws Exception {
		List<BoardDto> boardList = boardService.listBoard(criteria);
		
		for(int i=0; i<boardList.size(); i++) {
			BoardDto boardDto = boardList.get(i);
			MemberDTO memberDto = memberService.selectMemberByNum(boardDto.getWriter());
			boardDto.setMemberDto(memberDto);
		}
		model.addAttribute("boardList", boardList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(boardService.countTotBoard());
		model.addAttribute("pageMaker", pageMaker);		

		return "/board/list";
	}
	
	@RequestMapping(value="/board/edit", method = RequestMethod.GET)
	public String editBoard(BoardDto bdto, Model model) throws Exception {
		BoardDto boardDto = boardService.readBoard(bdto.getNum());
		MemberDTO memberDto = memberService.selectMemberByNum(boardDto.getWriter());
		boardDto.setMemberDto(memberDto);
		model.addAttribute("boardDto", boardDto);
		
		List<Map<String, Object>> fileList = boardService.selectFileList(boardDto.getNum());
		model.addAttribute("fileList", fileList);
		return "/board/edit";
	}
	
	@RequestMapping(value="/board/edit", method = RequestMethod.POST)
	public String editBoard(Model model, @Valid BoardDto boardDto, MultipartHttpServletRequest mpRequest,
							BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			return "/board/edit";
		}
		boardService.editBoard(boardDto, mpRequest);
		return "redirect:/board/read/"+boardDto.getNum();

	}
	
	@RequestMapping(value="/board/delete", method = RequestMethod.POST)
	public String deleteBoard(StudyroomDto studyroomDto) throws Exception {
		boardService.deleteFile(studyroomDto.getNum());
		boardService.deleteBoard(studyroomDto.getNum());
		
		
		File fileDir = new File(filePath + "\\" + studyroomDto.getNum());
		if(fileDir.exists()) {
			FileUtils.deleteDirectory(fileDir);
		}

		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/downFile/{FILE_NO}")
	public void downFile(@RequestParam Map<String, Object> map, HttpServletResponse response,
						@PathVariable("FILE_NO") int fileNum) throws Exception{
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
	
	@RequestMapping(value="/board/delFile/{FILE_NO}")
	public String delFile(@PathVariable("FILE_NO") int fileNum, Model model) throws Exception {
		Map<String, Object> resultMap = boardService.getFileInfoByFileNum(fileNum);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		int boardNum = Integer.parseInt(String.valueOf(resultMap.get("BOARD_NUM")));
		
		File target = new File(filePath + "\\" + boardNum + "\\" + storedFileName);
		if(target.exists()) {
			target.delete();
		}
		boardService.deleteFile(boardNum);
		
		return "redirect:/board/edit?" + "num=" + boardNum;
	}
}



