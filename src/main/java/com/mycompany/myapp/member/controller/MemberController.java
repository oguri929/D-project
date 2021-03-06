package com.mycompany.myapp.member.controller;




import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.myapp.chat.domain.Chat;
import com.mycompany.myapp.chat.service.ChatService;
import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;
import com.mycompany.myapp.member.service.MemberService;
import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.service.QuizService;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.service.StudyroomService;




@Controller
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService memberService;
	private QuizService quizService;
	private StudyroomService studyroomService;
	private ChatService chatService;
	
	@Autowired
	public MemberController(MemberService memberService, QuizService quizService, StudyroomService studyroomService,
			ChatService chatService) {
		super();
		this.memberService = memberService;
		this.quizService = quizService;
		this.studyroomService = studyroomService;
		this.chatService = chatService;
	}
	
	// ?????????
	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String login(MemberDTO dto) {
		logger.info("post login");
		
		return "user/login";
	}
	
	//
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String login(MemberDTO dto, HttpServletRequest request, Model model,RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		
		// ?????? ??????
		HttpSession session = request.getSession();
		
		MemberDTO login = memberService.login(dto);
		
		System.out.println(dto.getPw());
		System.out.println(login.getPw());
		if(!BCrypt.checkpw(dto.getPw(), login.getPw())) {
			// ?????? ??????
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", false);
			// ???????????? ?????? login ?????? null ?????????, msg ?????? ????????? false??????
			// ?????? ???????????? ????????????. ??? ?????? ?????? ???????????? ??????????????? ??????????????? ?????? ???????????? ??????????????????.
	} else {
		System.out.println("login succece");
		
			List<MemberDtoContainStudyroom> chatroomList=memberService.getMemList(login);
			System.out.println("login session: " +chatroomList.size());
			Map<Integer,String> subList=new HashMap<Integer,String>();
			for (MemberDtoContainStudyroom sub : chatroomList) {
				if(!subList.containsKey(sub.getSubjectNum())) {
					subList.put(sub.getSubjectNum(), sub.getSubject());
				}
			}
			System.out.println("login session: " +subList.size());
			
			session.setAttribute("chatroomList", chatroomList);
			session.setAttribute("subList", subList);
			
			session.setAttribute("user", login);
			model.addAttribute("user", login);
			//rttr.addFlashAttribute("user",login);
			return "redirect:/";
	}
	return "redirect:/login.do";
	
	/*???????????? ???????????? ????????? ?????? ???????????? ????????? null??? ??????
	 * ???????????? ????????? ?????? ???????????? ?????? ????????? ???????????? ??????.
	 * 
	 * ???????????? ????????? ??? ?????? ????????? ???????????? ????????????,
	 * ?????? ?????? ?????? user??? ?????? ?????? ??????.
	 * user?????? ????????? ?????? ???????????? ????????? ????????? ??????.*/ 
}




	// ???????????? ??????
	@RequestMapping(value="logout.do",method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		logger.info("get logout");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//???????????????
		@RequestMapping(value="/mypage.do")
		public String myPage() {
			return "/user/mypage";
		}
	
	
	// ???????????? GET	
	@RequestMapping(value="/user/register.do",method = RequestMethod.GET)
	public String registerGET() throws Exception {
		return "user/register";
	}
	
	// ???????????? POST
	@RequestMapping(value="/user/register.do",method = RequestMethod.POST)
	public String registerPOST(MemberDTO dto,RedirectAttributes redirectAttributes, HttpServletRequest req,Model model) throws Exception {
		System.out.println("register post");
		HttpSession session=req.getSession();
		if(session.getAttribute("idCheck")!=null) {
			String h_area1 = req.getParameter("h_area1");
			String h_area2 = req.getParameter("h_area2");
			dto.setLocal(h_area1 + " " + h_area2);
			String hashedPw = BCrypt.hashpw(dto.getPw(),BCrypt.gensalt());
			dto.setPw(hashedPw);
			//memberService.register(dto);
			redirectAttributes.addFlashAttribute("msg","REGISTERED");
			
			int result = memberService.idCheck(dto.getId());
			try {
				if(result == 1) {
					return "/user/login";
				}else if(result == 0) {
					memberService.register(dto);
				}
				// ???????????? ~ ????????? ???????????? ??????????????? -> ?????? ???????????? ???????????? ????????????
				// ???????????? ???????????? -> register
			} catch(Exception e) {
				throw new RuntimeException();
			}
			return "redirect:/login.do";
		}else {
			model.addAttribute("msg","??????????????? ?????? ??????????????????");
			return "/user/register";
			
		}
	}
	
	// ???????????? ?????? GET
	@RequestMapping(value="/user/update.do",method = RequestMethod.GET)
	public String registerUpdateView(HttpSession session,Model model) throws Exception {
		MemberDTO mDto=(MemberDTO) session.getAttribute("user");
		model.addAttribute("user",mDto);
		return "user/update";
	}
	
	// ???????????? ?????? POST
	@RequestMapping(value="/user/update.do", method=RequestMethod.POST)
	public String registerUpdate(MemberDTO dto, HttpSession session, HttpServletRequest req) throws Exception {
		String h_area1 = req.getParameter("h_area1");
		String h_area2 = req.getParameter("h_area2");
		dto.setLocal(h_area1 + " " + h_area2);
		
		String hashedPw = BCrypt.hashpw(dto.getPw(),BCrypt.gensalt());
		dto.setPw(hashedPw);
		memberService.memberUpdate(dto);
		//session.invalidate();
		session.setAttribute("user", dto);
		return "redirect:/";
	}
	

	//?????? ????????? ???????????? ?????? ????????????
	@RequestMapping(value="/user/getStudyrooms", method = RequestMethod.GET)
	public String getMyStudyrooms(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		MemberDTO user = (MemberDTO)session.getAttribute("user");
		List<Integer> cntRollInStudy=studyroomService.selectMemberFromAllMatch(user.getNum());
		List<ChatRoomInfoOfMember> chatroomList;
		if(cntRollInStudy.size()!=0) {
			chatroomList = quizService.getChatroomListofMember(user.getNum());
		}else {
			chatroomList=null;
		}
		model.addAttribute("chatroomList", chatroomList);
		
		return "/user/chatroomList";
	}
	
	// ????????? ????????????
	// ResponseBody??? ??????????????? ????????? ????????? ??? ??? ???????????? ?????????????????????
	@ResponseBody
	@RequestMapping(value="/user/idCheck.do",method=RequestMethod.POST)
	public int postIdCheck(String id,HttpSession session) throws Exception {
		System.out.println("test");
		int result = memberService.idCheck(id);
		session.setAttribute("idCheck", result);
		return result;
	}
	
	// ????????? ?????? ????????? ??????
	@RequestMapping(value = "/user/GoFindId.do")
	public String GofindId() throws Exception {
		return "/user/findIdForm";
	}
	
	// ????????? ?????? ??????
	@RequestMapping(value="/user/FindId.do", method = RequestMethod.POST)
	public String findIdAction(MemberDTO dto, Model model) throws Exception{
		MemberDTO user = memberService.userFindId(dto);
		
		if(user == null) {
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("id", user.getId());
		}
		return "/user/findIdForm";
	}
	
	// ???????????? ?????? ????????? ??????
	@RequestMapping(value="/user/findpw.do",method = RequestMethod.GET)
	public String GofindPw() throws Exception {
		return "/user/findPwForm";
	}
	
	// ???????????? ?????? ??????
	@RequestMapping(value ="/user/findpw.do", method = RequestMethod.POST)
	public void findPwAction(@ModelAttribute MemberDTO dto, HttpServletResponse response) throws Exception{
		memberService.userFindPw(response, dto);

	}
	
	//???????????? ???
	@RequestMapping(value="/user/deleteMember", method = RequestMethod.GET)
	public String deleteMember(Model model) {
		model.addAttribute("dto", new MemberDTO());
		return "/user/deleteMember";	
	}
	
	//???????????? ??????
	@RequestMapping(value="/user/deleteMember", method = RequestMethod.POST)
	public String deleteMember(MemberDTO dto, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		//????????? ?????? ?????????
		String id = user.getId();
		//????????? ?????? ????????????
		String oldPass = user.getPw();
		//?????? ????????? ????????????
		String newPass = dto.getPw();
		boolean result = BCrypt.checkpw(newPass, oldPass);
		
		//????????? ?????? ?????? ??????
		int memberNum = user.getNum();
		
		if(result) {
			try {
				//1. ?????? ??????????????? ????????? ??????
				List<Integer> caproomList = studyroomService.selectStudyroomByCaptain(memberNum);
				if(!(caproomList.size() == 0) || !(caproomList.isEmpty())) {
					for(int i : caproomList) {
						studyroomService.deleteAllMember(i);
						chatService.deleteChattingBySr_no(i);
						quizService.deleteQuizBySr_no(i);
						studyroomService.deleteStudyroom(i);
					}
				}
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
				//2. ?????? ?????? ??????????????? ????????? ??????
				List<Integer> roomList = studyroomService.selectMemberFromAllMatch(memberNum);
				if(!(roomList.size() == 0) || !(roomList.isEmpty())) {
						studyroomService.deleteMemberFromAllMatch(memberNum);
						quizService.deleteQuizByMaker(memberNum);
				}
				
				//3. ?????? ??????
				memberService.deleteMember(id);
				session.invalidate();
				System.out.println("???????????? ??????");
				
			} catch (Exception e) {
				e.printStackTrace();
			}		
			return "redirect:/";
		}else {
			return "redirect:/user/deleteMember";
		}
		
	}
	
	
	
	
}