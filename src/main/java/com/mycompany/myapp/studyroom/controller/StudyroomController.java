package com.mycompany.myapp.studyroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;
import com.mycompany.myapp.member.service.MemberService;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.studyroom.service.StudyroomService;
import com.mycompany.myapp.utils.PageMaker;
import com.mycompany.myapp.utils.SearchCriteria;

@Controller("studyroomController")
public class StudyroomController {
	private StudyroomService studyroomService;
	private MemberService memberService;

	@Autowired
	public StudyroomController(StudyroomService studyroomService, MemberService memberService) {
		super();
		this.studyroomService = studyroomService;
		this.memberService = memberService;
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
//		String addSubject = req.getParameter("addSubject");
//		
//		if(addSubject != null) {
//			studyroomService.addSubject(addSubject);
//			int subjectNum = studyroomService.getSubjectByName(addSubject);
//			studyroomDto.setSubjectNum(subjectNum);
//		}
		
		studyroomService.createStudyroom(studyroomDto);
		Map<String, Integer> matchInfo = new HashMap<String, Integer>();
		matchInfo.put("chatroomNum", studyroomDto.getNum());
		matchInfo.put("memberNum", studyroomDto.getCaptain());
		studyroomService.addMember(matchInfo);

		return "redirect:/studyroom/list";
	}
	
	@RequestMapping(value =  "/studyroom/list", method = RequestMethod.GET)
	public String listStudyroom(Model model, SearchCriteria scri) throws Exception {
		List<StudyroomDto> studyroomList = studyroomService.listStudyroom(scri);
		
		List<SubjectDto> subjectList =  studyroomService.getSubjectList();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(studyroomService.countTotStudyroom(scri));
		
		for(int i=0; i<studyroomList.size(); i++) {
			//각 스터디룸에 해당하는 과목 정보 입력
			StudyroomDto studyroomDto = studyroomList.get(i);
			int subjNum = studyroomDto.getSubjectNum();
			SubjectDto subjectDto = studyroomService.getSubject(subjNum);
			studyroomDto.setSubjectDto(subjectDto);
			//각 스터디룸에 속해있는 총 멤버 수 입력
			int totMember = studyroomService.countTotMember(studyroomDto.getNum());
			studyroomDto.setTotMember(totMember);
			//작성자 정보 가져오기
			MemberDTO memberDto = memberService.selectMemberByNum(studyroomDto.getCaptain());
			studyroomDto.setMemberDto(memberDto);
		}
		model.addAttribute("scri",scri);
		model.addAttribute("studyroomList", studyroomList);
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("pageMaker", pageMaker);
		return "/studyroom/list";
	}
	
	@RequestMapping(value="/studyroom/read/{num}", method = RequestMethod.GET)
	public String readStudyroom(@PathVariable int num, Model model) throws Exception {
		StudyroomDto studyroomDto = studyroomService.readStudyroom(num);
		SubjectDto subjectDto = studyroomService.getSubject(studyroomDto.getSubjectNum());
		studyroomDto.setSubjectDto(subjectDto);
		int totMember = studyroomService.countTotMember(studyroomDto.getNum());
		studyroomDto.setTotMember(totMember);
		MemberDTO memberDto = memberService.selectMemberByNum(studyroomDto.getCaptain());
		studyroomDto.setMemberDto(memberDto);
		model.addAttribute("studyroomDto", studyroomDto);
		
		List<MemberDTO> memberList = studyroomService.getMemberList(num);
		model.addAttribute("memberList", memberList);
	
		return "/studyroom/read";
	}
	
	@RequestMapping(value="/studyroom/edit", method = RequestMethod.GET)
	public String editStudyroom(StudyroomDto sdto, Model model) throws Exception {
		StudyroomDto studyroomDto = studyroomService.readStudyroom(sdto.getNum());
		MemberDTO memberDto = memberService.selectMemberByNum(studyroomDto.getCaptain());
		studyroomDto.setMemberDto(memberDto);
		model.addAttribute("studyroomDto", studyroomDto);

		List<SubjectDto> subjectList = studyroomService.getSubjectList();
		model.addAttribute("subjectList", subjectList);
		
		List<MemberDTO> memberList = studyroomService.getMemberList(studyroomDto.getNum());
		model.addAttribute("memberList", memberList);
		
		return "/studyroom/edit";
	}
	
	@RequestMapping(value = "/studyroom/edit", method = RequestMethod.POST)
	public String editStudyroom(StudyroomDto studyroomDto, HttpServletRequest request) {
		studyroomService.editStudyroom(studyroomDto);
	
		return "redirect:/studyroom/list";
	}
	
	@RequestMapping(value = "/studyroom/delete", method = RequestMethod.POST)
	public String deleteStudyroom(StudyroomDto studyroomDto) {
		studyroomService.deleteStudyroom(studyroomDto.getNum());

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
		
		HttpSession session = req.getSession();
		MemberDTO user = (MemberDTO)session.getAttribute("user");
		List<MemberDtoContainStudyroom> chatroomList = memberService.getMemList(user);
		session.setAttribute("chatroomList", chatroomList);
		Map<Integer,String> subList=new HashMap<Integer,String>();
		for (MemberDtoContainStudyroom sub : chatroomList) {
			if(!subList.containsKey(sub.getSubjectNum())) {
				subList.put(sub.getSubjectNum(), sub.getSubject());
			}
		}
		session.setAttribute("subList", subList);
		
//		int memberLimit = studyroomDto.getMemberLimit();
//		int totMember = studyroomService.countTotMember(chatroomNum);
//		if(totMember < memberLimit) {			
//			studyroomService.addMember(matchInfo);
//			return "redirect:/studyroom/read/"+chatroomNum;
//		}
		
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
		
		HttpSession session = req.getSession();
		MemberDTO user = (MemberDTO)session.getAttribute("user");
		List<MemberDtoContainStudyroom> chatroomList = memberService.getMemList(user);
		session.setAttribute("chatroomList", chatroomList);
		Map<Integer,String> subList=new HashMap<Integer,String>();
		for (MemberDtoContainStudyroom sub : chatroomList) {
			if(!subList.containsKey(sub.getSubjectNum())) {
				subList.put(sub.getSubjectNum(), sub.getSubject());
			}
		}
		session.setAttribute("subList", subList);
		
		StudyroomDto studyroomDto = studyroomService.readStudyroom(chatroomNum);
		SubjectDto subjectDto = studyroomService.getSubject(studyroomDto.getSubjectNum());
		studyroomDto.setSubjectDto(subjectDto);
		List<MemberDTO> memberList = studyroomService.getMemberList(chatroomNum);
		
		model.addAttribute(studyroomDto);
		model.addAttribute(memberList);
		return "/studyroom/read";
	}
		
}


