package com.mycompany.myapp.studyroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.studyroom.service.StudyroomService;
import com.mycompany.myapp.utils.PageMaker;
import com.mycompany.myapp.utils.SearchCriteria;

@Controller("studyroomController")
public class StudyroomController {
	private StudyroomService studyroomService;

	public StudyroomController(StudyroomService studyroomService) {
		this.studyroomService = studyroomService;
	}
	
	@RequestMapping(value = "/studyroom/create", method = RequestMethod.GET)
	public String createStudyroom(Model model) {
		List<SubjectDto> subjectList = studyroomService.getSubjectList();
		
		model.addAttribute("studyroomDto", new StudyroomDto());
		model.addAttribute("subjectList", subjectList);
		return "/studyroom/create";
	}
	
	@RequestMapping(value = "/studyroom/create", method = RequestMethod.POST)
	public String createStudyroom(StudyroomDto studyroomDto, HttpServletRequest req) {
		String addSubject = req.getParameter("addSubject");
		
		if(addSubject != null) {
			studyroomService.addSubject(addSubject);
			int subjectNum = studyroomService.getSubjectByName(addSubject);
			studyroomDto.setSubjectNum(subjectNum);
		}
		
		studyroomService.createStudyroom(studyroomDto);
		Map<String, Integer> matchInfo = new HashMap<String, Integer>();
		matchInfo.put("chatroomNum", studyroomDto.getNum());
		matchInfo.put("memberNum", studyroomDto.getCaptain());
		studyroomService.addMember(matchInfo);

		return "redirect:/studyroom/list";
	}
	
	@RequestMapping(value =  "/studyroom/list", method = RequestMethod.GET)
	public String listStudyroom(Model model, SearchCriteria scri) {
		List<StudyroomDto> studyroomList = studyroomService.listStudyroom(scri);
		
		List<SubjectDto> subjectList =  studyroomService.getSubjectList();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(studyroomService.countTotStudyroom(scri));
		
		for(int i=0; i<studyroomList.size(); i++) {
			StudyroomDto studyroomDto = studyroomList.get(i);
			int subjNum = studyroomDto.getSubjectNum();
			SubjectDto subjectDto = studyroomService.getSubject(subjNum);
			studyroomDto.setSubjectDto(subjectDto);
		}
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("studyroomList", studyroomList);
		model.addAttribute("pageMaker", pageMaker);
		return "/studyroom/list";
	}
	
	@RequestMapping(value="/studyroom/read/{num}")
	public String readStudyroom(@PathVariable int num, Model model) {
		StudyroomDto studyroomDto = studyroomService.readStudyroom(num);
		SubjectDto subjectDto = studyroomService.getSubject(studyroomDto.getSubjectNum());
		studyroomDto.setSubjectDto(subjectDto);
		List<MemberDTO> memberList = studyroomService.getMemberList(num);
		
		model.addAttribute("studyroomDto", studyroomDto);
		model.addAttribute("memberList", memberList);
	
		return "/studyroom/read";
	}
	
	@RequestMapping(value="/studyroom/edit/{num}", method = RequestMethod.GET)
	public String editStudyroom(@PathVariable int num, Model model) {
		StudyroomDto studyroomDto = studyroomService.readStudyroom(num);
		List<SubjectDto> subjectList = studyroomService.getSubjectList();

		model.addAttribute("studyroomDto", studyroomDto);
		model.addAttribute("subjectList", subjectList);
		
		return "/studyroom/edit";
	}
	
	@RequestMapping(value = "/studyroom/edit/{num}", method = RequestMethod.POST)
	public String editStudyroom(StudyroomDto studyroomDto, HttpServletRequest request) {
		studyroomService.editStudyroom(studyroomDto);
	
		return "redirect:/studyroom/list";
	}
	
	@RequestMapping(value = "/studyroom/delete/{num}", method = RequestMethod.GET)
	public String deleteStudyroom(@PathVariable int num, Model model) {
		model.addAttribute("num", num);
		
		return "/studyroom/delete";
	}
	
	@RequestMapping(value = "/studyroom/delete", method = RequestMethod.POST)
	public String deleteStudyroom(int num) {
		studyroomService.deleteStudyroom(num);

		return "redirect:/studyroom/list";
	}
	
	@RequestMapping(value = "/studyroom/search/{num}")
	public String searchByTag(@PathVariable int num, Model model) {
		List<StudyroomDto> studyroomList = studyroomService.listStudyroom(num);
		
		List<SubjectDto> subjectList =  studyroomService.getSubjectList();
		
		for(int i=0; i<studyroomList.size(); i++) {
			StudyroomDto studyroomDto = studyroomList.get(i);
			int subjNum = studyroomDto.getSubjectNum();
			SubjectDto subjectDto = studyroomService.getSubject(subjNum);
			studyroomDto.setSubjectDto(subjectDto);
		}
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("studyroomList", studyroomList);
		
		return "/studyroom/list";
	}
	
	@RequestMapping(value = "/studyroom/register")
	public String registerMember(HttpServletRequest req, Model model) {
		int memberNum = Integer.valueOf(req.getParameter("memberNum"));
		int chatroomNum = Integer.valueOf(req.getParameter("chatroomNum"));
		Map<String, Integer> matchInfo = new HashMap<String, Integer>();
		matchInfo.put("memberNum", memberNum);
		matchInfo.put("chatroomNum", chatroomNum);
		
		studyroomService.addMember(matchInfo);
		
		StudyroomDto studyroomDto = studyroomService.readStudyroom(chatroomNum);
		SubjectDto subjectDto = studyroomService.getSubject(studyroomDto.getSubjectNum());
		studyroomDto.setSubjectDto(subjectDto);
		List<MemberDTO> memberList = studyroomService.getMemberList(chatroomNum);
		
		model.addAttribute(studyroomDto);
		model.addAttribute(memberList);
		return "/studyroom/read";
	}
	
	@RequestMapping(value = "/studyroom/leave")
	public String deleteMember(HttpServletRequest req, Model model) {
		int memberNum = Integer.valueOf(req.getParameter("memberNum"));
		int chatroomNum = Integer.valueOf(req.getParameter("chatroomNum"));
		Map<String, Integer> matchInfo = new HashMap<String, Integer>();
		matchInfo.put("memberNum", memberNum);
		matchInfo.put("chatroomNum", chatroomNum);
		
		studyroomService.deleteMember(matchInfo);
		
		StudyroomDto studyroomDto = studyroomService.readStudyroom(chatroomNum);
		SubjectDto subjectDto = studyroomService.getSubject(studyroomDto.getSubjectNum());
		studyroomDto.setSubjectDto(subjectDto);
		List<MemberDTO> memberList = studyroomService.getMemberList(chatroomNum);
		
		model.addAttribute(studyroomDto);
		model.addAttribute(memberList);
		return "/studyroom/read";
	}
		
}


