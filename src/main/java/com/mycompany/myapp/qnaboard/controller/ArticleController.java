package com.mycompany.myapp.qnaboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.myapp.qnaboard.domain.ArticleVO;
import com.mycompany.myapp.qnaboard.domain.ReplyVO;
import com.mycompany.myapp.qnaboard.service.ArticleService;
import com.mycompany.myapp.qnaboard.service.ReplyService;



@Controller
public class ArticleController {
	@Inject
	ArticleService service;
	
	@Inject
	ReplyService replyService;
	
	//작성 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public String writeView() throws Exception{
		return "qnaboard/writeView";
	}
	
	// 작성
	@RequestMapping(value = "/writeView", method = RequestMethod.POST)
	public String write(ArticleVO articleVO) throws Exception{
		service.write(articleVO);
		return "redirect:qnaboard/writeList";
	}
	
	//리스트 조회
	@RequestMapping(value = "/writeList", method = RequestMethod.GET)
	public String list(Model model) throws Exception{
		model.addAttribute("list",service.list());
		return "/qnaboard/writeList";
	}
	
	// 게시판 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(ArticleVO articleVO, Model model) throws Exception{
		model.addAttribute("read", service.read(articleVO.getBno()));
		List<ReplyVO> replyList = replyService.readReply(articleVO.getBno());
		model.addAttribute("replyList", replyList);
		return "/qnaboard/readView";
	}
	
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(ArticleVO articleVO, Model model) throws Exception{
		model.addAttribute("update", service.read(articleVO.getBno()));
		return "/qnaboard/updateView";
	}
	
	// 게시판 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ArticleVO articleVO) throws Exception{
		service.update(articleVO);
		return "redirect:/qnaboard/writeList";
	}
	
	// 게시판 삭제
	@RequestMapping(value = "/deleteView", method = RequestMethod.POST)
	public String delete(ArticleVO articleVO) throws Exception{
		service.delete(articleVO.getBno());
		return "redirect:/qnaboard/writeList";
	}
	
	//댓글 달기
	@RequestMapping(value="/replyWrite", method = RequestMethod.POST)
	public String replyWrite(ReplyVO vo, RedirectAttributes rttr) throws Exception {
		replyService.writeReply(vo);
		rttr.addAttribute("bno", vo.getBno());
		return "redirect:/qnaboard/readView";
	}
}
